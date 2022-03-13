package tw.com.fcb.lion.core.ir.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.fcb.lion.core.ir.repository.IRMasterRepository;
import tw.com.fcb.lion.core.ir.repository.IRSwiftMessageRepository;
import tw.com.fcb.lion.core.ir.repository.entity.IRMaster;
import tw.com.fcb.lion.core.ir.repository.entity.IRSwiftMessage;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;

@Transactional
@Service
public class IRService {

	@Autowired
	IRSwiftMessageRepository repository;

	@Autowired
	IRMasterRepository IRMasterRepository;
	
	public void insert(SwiftMessageSaveCmd saveCmd) {
		IRSwiftMessage entity = new IRSwiftMessage();
		BeanUtils.copyProperties(saveCmd, entity);
		repository.save(entity);
	}

	//傳入受通知單位查詢案件數
	public Integer getIrCaseCount(String branch) {
		Integer count = 0;
		count = IRMasterRepository.findByBeAdvisingBranch(branch).size();
		return count;
	}
	
	// 新增IRMASTER
	public void insertIrMaster(IRSaveCmd saveCmd) {
		IRMaster entityCmd = new IRMaster();
		BeanUtils.copyProperties(saveCmd, entityCmd);
		IRMasterRepository.save(entityCmd);
	}
	
	//傳入外匯編號，執行匯入解款
	public void settle(String irNo) {
		IRMaster irMaster = new IRMaster();
		irMaster = IRMasterRepository.findByirNo(irNo);
		irMaster.setPaidStats("2");
		IRMasterRepository.save(irMaster);
	}
}
