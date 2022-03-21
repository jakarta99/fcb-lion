package tw.com.fcb.lion.core.ir.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.fcb.lion.core.commons.http.DateConverter;
import tw.com.fcb.lion.core.ir.ChargeType;
import tw.com.fcb.lion.core.ir.repository.FXRateRepository;
import tw.com.fcb.lion.core.ir.repository.IRMasterRepository;
import tw.com.fcb.lion.core.ir.repository.IRSwiftMessageRepository;
import tw.com.fcb.lion.core.ir.repository.entity.FXRateVo;
import tw.com.fcb.lion.core.ir.repository.entity.IRMaster;
import tw.com.fcb.lion.core.ir.repository.entity.IRSwiftMessage;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;

@Transactional
@Service
public class IRSwiftMessageCheckService {

	@Autowired
	IRSwiftMessageRepository repository;

	@Autowired
	IRMasterRepository IRMasterRepository;
	
	@Autowired
	private FXRateRepository fxRateRepository;
	
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
	            System.out.println(lineData);

				// split 切割
				String[] data = lineData.split(",");

				// 設值
				SwiftMessageSaveCmd messageSaveCmd = new SwiftMessageSaveCmd();
				if (idx == 1){
					continue;
				}else{
					messageSaveCmd.setSeqNo(data[0]);
					messageSaveCmd.setSenderSwiftCode(data[1]);
					messageSaveCmd.setReferenceNo(data[2]);
					messageSaveCmd.setValueDate(new DateConverter().convert(data[3]));
					messageSaveCmd.setChargeType(ChargeType.SHA);
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
		BeanUtils.copyProperties(saveCmd, entity);
		repository.save(entity);
	}

	//傳入受通知單位查詢案件數
	public Integer getIrCaseCount(String branch,String printAdvMk) {
		Integer count = 0;
		count = IRMasterRepository.findByBeAdvisingBranchAndPrintAdvisingMk(branch,printAdvMk).size();
		return count;
	}
	
	// 檢核成功，新增資料至IRMASTER
	public void insertIrMaster(IRSaveCmd irSaveCmd) throws Exception {	
		checkIrMaster(irSaveCmd);
		
		IRMaster entityCmd = new IRMaster();
		BeanUtils.copyProperties(irSaveCmd, entityCmd);
		IRMasterRepository.save(entityCmd);
	}
	
	// 檢核主檔資料
	public void checkIrMaster(IRSaveCmd irSaveCmd) throws Exception{
		String[] branchs = {"091", "093", "094"};
		String[] currencys = {"USD", "GBP", "JPY"};
		String[] customers = {"86483666", "05052322", "03218306"};

		var adversingBranch = irSaveCmd.getBeAdvisingBranch();
		if(!Arrays.asList(branchs).contains(adversingBranch)) {
			throw new Exception("分行別錯誤");
		}
			
		var currency  = irSaveCmd.getCurrency();
		if(!Arrays.asList(currencys).contains(currency)) {
			throw new Exception("幣別錯誤");
		}
		else {
			FXRateVo fxRateVo = fxRateRepository.findByCurrency(currency);
			irSaveCmd.setExchangeRate(fxRateVo.getCostSpotBoughFxRate());
		}
			
		var customerId = irSaveCmd.getCustomerId();
		if(!Arrays.asList(customers).contains(customerId)) {
			throw new Exception("統編錯誤");
		}
	}
	
	//傳入ID查詢內容
	public IR getById(Long id) {
		IRMaster iRMaster = IRMasterRepository.findById(id).orElseThrow( () -> new RuntimeException("errID") );
		IR ir = new IR();
		BeanUtils.copyProperties(iRMaster, ir);
		return ir;
	}
	
}
