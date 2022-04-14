package tw.com.fcb.lion.core.ir.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.lion.core.commons.http.DateConverter;
import tw.com.fcb.lion.core.ir.ChargeType;
import tw.com.fcb.lion.core.ir.repository.IRMasterRepository;
import tw.com.fcb.lion.core.ir.repository.IRSwiftMessageRepository;
import tw.com.fcb.lion.core.ir.repository.entity.Branch;
import tw.com.fcb.lion.core.ir.repository.entity.IRMaster;
import tw.com.fcb.lion.core.ir.repository.entity.IRSwiftMessage;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;
import tw.com.fcb.lion.core.sharedkernel.api.MainFrameClient;

@Transactional
@Service
@RequiredArgsConstructor
public class IRSwiftMessageCheckService {

	@Autowired
	IRSwiftMessageRepository repository;

	@Autowired
	IRMasterRepository IRMasterRepository;
	
	@Autowired
	IRPaymentService irPaymentService;
	
	@Autowired
	CommonCheckService commonCheckService;
	
	final MainFrameClient mainFrameClient;
	
	Logger log = LoggerFactory.getLogger(getClass());

	//讀取swift電文資料
	public List<SwiftMessageSaveCmd> loadFromFile() throws IOException {
		List<SwiftMessageSaveCmd> swiftMessage = new ArrayList<SwiftMessageSaveCmd>();
		// 讀檔
		File file = new File("C:\\data\\swiftMessage.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String lineData;
		int idx = 0;
		// 迴圈讀一行資料
		while ( (lineData = br.readLine()) != null){
			idx++;
	        
			// split 切割
			String[] data = lineData.split(",");

			SwiftMessageSaveCmd messageSaveCmd = null;
			if (idx == 1){
				continue;
			}else{
//				電文內容 - 設值
				String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//				帳號強制11碼 - 右靠左補零
				String account = String.format("%011d", Long.valueOf(data[5]));
//				手續費類型
				ChargeType chargeType = null;
				try {
					chargeType = ChargeType.valueOf(data[24]);
				} catch (Exception e) {
						log.debug(e + "資料型態（chargeType）不符EUNM定義=" + data[24]);
					chargeType = ChargeType.valueOf("SHA");
				}
					
				messageSaveCmd = SwiftMessageSaveCmd.builder().seqNo(data[0]).currency(data[1])
						        .irAmt(new BigDecimal(data[2])).valueDate(new DateConverter().convert(data[4]))
						        .beneficiaryAccount(account).chargeType(chargeType).remitSwiftCode(data[34])
						        .stats("1").txTime(time).build();
				
			}

			// 放到 List 之中
			swiftMessage.add(messageSaveCmd);
		}
		fr.close();
		br.close();
		return swiftMessage;
	}
		
	public void insert(SwiftMessageSaveCmd saveCmd) {
		IRSwiftMessage entity = new IRSwiftMessage();
		;
		BeanUtils.copyProperties(saveCmd, entity);
//		因SwiftMessageSaveCmd(ChargeType)為EUNM，IRSwiftMessage(ChargeType)為String，故需轉換
		//entity.setChargeType(String.valueOf(saveCmd.getChargeType()));
		repository.save(entity);
	}

	//傳入受通知單位查詢案件數
	public Integer getIrCaseCount(String branch,String printAdvMk) {
		Integer count = 0;
		count = IRMasterRepository.findByBeAdvisingBranchAndPrintAdvisingMk(branch,printAdvMk).size();
		return count;
	}
	
	//傳入ID查詢內容
	public IR getById(Long id) {
		IRMaster iRMaster = IRMasterRepository.findById(id).orElseThrow( () -> new RuntimeException("errID") );
		IR ir = new IR();
		BeanUtils.copyProperties(iRMaster, ir);
		return ir;
	}
	
	//傳入SEQ_NO (SWIFT序號) 查詢內容
	public SwiftMessageSaveCmd getBySwiftMessageSeqNo(String seqNo) {
		IRSwiftMessage irSwiftMessage = repository.findBySeqNo(seqNo).orElseThrow( () -> new RuntimeException("SWIFT序號不存在") );
		SwiftMessageSaveCmd swiftmessage = SwiftMessageSaveCmd.builder().build();
		BeanUtils.copyProperties(irSwiftMessage, swiftmessage);
		
		return swiftmessage;
	}
	
	// 檢核成功，新增資料至IRMASTER
	Boolean isBeAdvisingBranch;
	Boolean isRemittanceTransfer;
	public IR insertIRMaster(String seqNo) throws Exception{
		IRSaveCmd irSaveCmd = new IRSaveCmd();
		IR ir = new IR();
		
		try {
			SwiftMessageSaveCmd swiftMessage = getBySwiftMessageSeqNo(seqNo);
			log.info("swiftMessage: {}", swiftMessage);
			validateSwiftMessage(swiftMessage);
			Boolean isAutoSettleCase = mainFrameClient.isAutoSettleCase(swiftMessage.getBeneficiaryAccount());
			
			if (isBeAdvisingBranch == true && isRemittanceTransfer == false) {
				swiftMessageCheckSuccess(swiftMessage, irSaveCmd);

			   //自動解款
				if (isAutoSettleCase == true) {
					swiftMessageAutoSettle(irSaveCmd);
				}

				updateSwiftMessageStatus(seqNo, "2");
				
				IRMaster entityCmd = new IRMaster();
				BeanUtils.copyProperties(irSaveCmd, entityCmd);
				IRMasterRepository.save(entityCmd);
				BeanUtils.copyProperties(entityCmd, ir);
			} 
			else {
				updateSwiftMessageStatus(seqNo, "3");
				ir.setStatus("3");
			}
		} 
		catch (Exception e) {
			log.error("insertIRMaster: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		
		return ir;
	}
	
//	Validate 檢核 SwiftMessage
	public void validateSwiftMessage(SwiftMessageSaveCmd swiftMessage) {
		isBeAdvisingBranch = mainFrameClient.isBeAdvisingBranch(swiftMessage.getBeneficiaryAccount());
		isRemittanceTransfer = mainFrameClient.isRemittanceTransfer(swiftMessage.getRemitSwiftCode());
	}
	
	public void swiftMessageCheckSuccess(SwiftMessageSaveCmd swiftMessage, IRSaveCmd irSaveCmd) throws Exception {
		BeanUtils.copyProperties(swiftMessage, irSaveCmd);
		
//		test
		irSaveCmd.setBeAdvisingBranch("094");
		irSaveCmd.setCustomerId("05052322");
		irSaveCmd.setAccountInstitution("string");
		irSaveCmd.setBeneficiaryAccount(swiftMessage.getBeneficiaryAccount());
		irSaveCmd.setSenderSwiftCode(swiftMessage.getRemitSwiftCode());
		irSaveCmd.setPrintAdvisingMk("N");
		
		String depositBank = mainFrameClient.getDepositBank(irSaveCmd.getSenderSwiftCode());
		String bankNameAndAddress = mainFrameClient.getBankNameAndAddress(irSaveCmd.getSenderSwiftCode());
		
		//取號程式
		var branch = commonCheckService.checkBranchCode(irSaveCmd.getBeAdvisingBranch());
		var branchSerialNo = generateIrno(branch);
		
		//客戶統編
		commonCheckService.checkCustomerId(irSaveCmd.getCustomerId());
		
		//匯率
		var fxRate = commonCheckService.checkCurrency(irSaveCmd.getCurrency());
		
		irSaveCmd.setIrNo(branchSerialNo);
		irSaveCmd.setExchangeRate(fxRate.getSpotBoughFxRate());
		irSaveCmd.setDepositBank(depositBank);
		irSaveCmd.setRemitBankInfo1(bankNameAndAddress);
		irSaveCmd.setStatus("2");
	}
	
	public void swiftMessageAutoSettle(IRSaveCmd irSaveCmd) {
		//自動印製通知書
		irSaveCmd.setPrintAdvisingMk("Y");
		irSaveCmd.setPrintAdvisingDate(LocalDate.now());
		
		//計算手續費
		BigDecimal irFee = irPaymentService.calculateOriginalCurrencyFee(irSaveCmd.getIrAmt(),irSaveCmd.getCurrency());
		irSaveCmd.setCommCharge(irFee);
		
		//付款狀態
		irSaveCmd.setPaidStats("2");
	}
	
	//分行取號作業
	public String generateIrno(Branch branch) {
		String branchSerialNo = new String();
		String branchSerialNo1 = "S1";
		String branchSerialNo2 = branch.getBranchTrack();
		
		// 號碼加一再取號
		commonCheckService.updateBranchSerialNo(branch);
		String branchSerialNo3 = branch.getBranchSerialNo().toString();
		
		String zeroString = new String();
		for(int i  = 0 ; i < (6 - branchSerialNo3.length()) ; i++) {
			zeroString = zeroString + "0";
		}
		branchSerialNo3 = zeroString.concat(branchSerialNo3);
		branchSerialNo = branchSerialNo1.concat(branchSerialNo2).concat(branchSerialNo3);
		
		return branchSerialNo;
	}
	
	//修改SwiftMessage
	public void updateSwiftMessage(SwiftMessageSaveCmd swiftMessage) {
		IRSwiftMessage IRswiftMessage = repository.findBySeqNo(swiftMessage.getSeqNo()).orElseThrow( () -> new RuntimeException("SWIFT序號不存在") );
		BeanUtils.copyProperties(swiftMessage, IRswiftMessage);
		repository.save(IRswiftMessage);		
	}
	
	//修改SwiftMessage狀態(2:成功、3：失敗)
	public void updateSwiftMessageStatus(String seqNo, String status) {
		SwiftMessageSaveCmd swiftMessage = getBySwiftMessageSeqNo(seqNo);
		IRSwiftMessage IRswiftMessage = new IRSwiftMessage();
		swiftMessage.setStats(status);
		BeanUtils.copyProperties(swiftMessage, IRswiftMessage);
		repository.save(IRswiftMessage);		
	}
	
	
}
