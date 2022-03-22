package tw.com.fcb.lion.core.ir.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
	public void updatePrintAdviceMark(String branch) {
		  List<IRMaster> irMaster = IRMasterRepository.findByBeAdvisingBranchAndPrintAdvisingMk(branch,"N");
		     for(IRMaster iRMaster : irMaster) {
			  	 iRMaster.setPrintAdvisingMk("Y");
				 iRMaster.setPrintAdvisingDate(LocalDate.now());				 
		     }
		     IRMasterRepository.saveAll(irMaster);	 
	}
	
	public IR queryIRmasterData(String irNo) {
		IRMaster irmaster = IRMasterRepository.findByIrNo(irNo);
		IR ir = new IR();
		BeanUtils.copyProperties(irmaster,ir);
		return ir;
	}
	//計算手續費
	public BigDecimal calculateFee(BigDecimal irAmt) {

		double standardCharge = irAmt.doubleValue()*0.0005;
//		暫訂最低收美金7元 (台幣200)，最高收美金28元(台幣800)
		if (standardCharge < 7){
			standardCharge = 7;
		} else { if (standardCharge > 28)
			standardCharge = 28;
		}
		return BigDecimal.valueOf(standardCharge);
	}
	//傳入外匯編號，執行匯入解款
	public void settle(IR ir) {
		IRMaster irmaster = IRMasterRepository.findByIrNo(ir.getIrNo());
		ir.setPaidStats("2");
		BeanUtils.copyProperties(ir,irmaster);
		IRMasterRepository.save(irmaster);
	}
}
