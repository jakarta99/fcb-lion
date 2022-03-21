package tw.com.fcb.lion.core.ir.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import tw.com.fcb.lion.core.ir.repository.IRMasterRepository;
import tw.com.fcb.lion.core.ir.repository.entity.IRMaster;
import tw.com.fcb.lion.core.ir.web.dto.IR;


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
	
	public IR queryIRmasterData(String irNo) {
		IRMaster irmaster = IRMasterRepository.findByIrNo(irNo);
		IR ir = new IR();
		BeanUtils.copyProperties(irmaster,ir);
		return ir;
	}
	//傳入外匯編號，執行匯入解款
	public void settle(IR ir) {
		IRMaster irmaster = IRMasterRepository.findByIrNo(ir.getIrNo());
		ir.setPaidStats("2");
		BeanUtils.copyProperties(ir,irmaster);
		IRMasterRepository.save(irmaster);
	}
}
