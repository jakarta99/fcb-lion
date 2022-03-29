package tw.com.fcb.lion.core.ir.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
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

import tw.com.fcb.lion.core.commons.http.DateConverter;
import tw.com.fcb.lion.core.ir.ChargeType;
import tw.com.fcb.lion.core.ir.repository.IRMasterRepository;
import tw.com.fcb.lion.core.ir.repository.IRSwiftMessageRepository;
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

			SwiftMessageSaveCmd messageSaveCmd = null;
			if (idx == 1){
				continue;
			}else{
//				電文內容 - 設值
				String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
				messageSaveCmd = messageSaveCmd.builder().seqNo(data[0]).currency(data[1])
						        .irAmt(new BigDecimal(data[2])).valueDate(new DateConverter().convert(data[4]))
						        .beneficiaryAccount(data[5]).chargeType(ChargeType.SHA).remitSwiftCode(data[34])
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
	public IR insertIrMaster(IRSaveCmd irSaveCmd){	
		IRMaster entityCmd = new IRMaster();
		BeanUtils.copyProperties(irSaveCmd, entityCmd);
		IRMasterRepository.save(entityCmd);
		
		IR ir = new IR();
		BeanUtils.copyProperties(entityCmd, ir);
		return ir;
	}
	
	//傳入ID查詢內容
	public IR getById(Long id) {
		IRMaster iRMaster = IRMasterRepository.findById(id).orElseThrow( () -> new RuntimeException("errID") );
		IR ir = new IR();
		BeanUtils.copyProperties(iRMaster, ir);
		return ir;
	}
	
	//傳入SEQ_NO (SWIFT序號) 查詢內容
	public IRSwiftMessage getBySwiftMessageSeqNo(String seqNo) {
		IRSwiftMessage irSwiftMessage = repository.findBySeqNo(seqNo).orElseThrow( () -> new RuntimeException("SWIFT序號不存在") );

		return irSwiftMessage;
	}
	
	//修改SwiftMessage狀態(2:成功、3：失敗)
	public void updateSwiftMessageStatus(String seqNo, String status) {
		IRSwiftMessage swiftMessage = getBySwiftMessageSeqNo(seqNo);
		swiftMessage.setStats(status);
		repository.save(swiftMessage);		
	}
}
