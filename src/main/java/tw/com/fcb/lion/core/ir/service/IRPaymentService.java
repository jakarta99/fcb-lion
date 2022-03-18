package tw.com.fcb.lion.core.ir.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import tw.com.fcb.lion.core.ir.repository.IRMasterRepository;
import tw.com.fcb.lion.core.ir.repository.entity.IRMaster;


@Transactional
@Service
public class IRPaymentService {

	@Autowired
	IRMasterRepository IRMasterRepository;
	
	//更新印製通知書記號
	public void updatePrintAdviceMark(Long id) {
		 var iRMaster = IRMasterRepository.findById(id).
				 orElseThrow(() -> new NotFoundException("查無ID" + id));
		 iRMaster.setPrintAdvisingMk("Y");
		 IRMasterRepository.save(iRMaster);
	}
	
	
	//傳入外匯編號，執行匯入解款
	public void settle(String irNo) {
		IRMaster irMaster = new IRMaster();
		irMaster = IRMasterRepository.findByIrNo(irNo);
		irMaster.setPaidStats("2");
		IRMasterRepository.save(irMaster);
	}
}
