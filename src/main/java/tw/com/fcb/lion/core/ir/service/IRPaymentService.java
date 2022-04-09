package tw.com.fcb.lion.core.ir.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.fcb.lion.core.ir.repository.FXRateRepository;
import tw.com.fcb.lion.core.ir.repository.IRMasterRepository;
import tw.com.fcb.lion.core.ir.repository.entity.FxRate;
import tw.com.fcb.lion.core.ir.repository.entity.IRMaster;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;
import tw.com.fcb.lion.core.ir.web.dto.IRQuery;


@Transactional
@Service
public class IRPaymentService {

	@Autowired
	IRMasterRepository IRMasterRepository;
	
	@Autowired
	FXRateRepository fxRateRepository;
	
	Logger log = LoggerFactory.getLogger(getClass());
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
	
	//模糊查詢匯入主檔資料
	public List<IRQuery> queryIRmasterDataLikeByirNo(String irNo) {
		List<IRMaster> irmasterlist = IRMasterRepository.findByIrNoLike(irNo);
		List<IRQuery> irquerylist = new ArrayList<IRQuery>();

		for(int i = 0; i < irmasterlist.size();i++) {
			IRMaster irmaster = irmasterlist.get(i);
			IRQuery irquery = new IRQuery();
			BeanUtils.copyProperties(irmaster,irquery);
			irquerylist.add(irquery);
		}
		return irquerylist;
	}
	
	//計算手續費
	public BigDecimal calculateFee(BigDecimal irAmt,String currency) {
		FxRate fxRate =  fxRateRepository.findByCurrency(currency)
				.orElseThrow(() -> new RuntimeException("無此幣別") );
		BigDecimal spotSoldFxRate = fxRate.getSpotSoldFxRate();
		BigDecimal toUSDfxRate = fxRate.getToUsdFxRate();
		log.info("fxRate:{} " , fxRate);
		log.info("spotSoldFxRate:{} " , spotSoldFxRate);
		log.info("toUsdfxRate:{} " , toUSDfxRate);
		
		
		BigDecimal feeRate = new BigDecimal("0.0005");
		BigDecimal standardCharge = irAmt.multiply(feeRate);
		BigDecimal toUSDStandardCharge = standardCharge.multiply(toUSDfxRate);
		Double toUSDStandardChargeDouble = toUSDStandardCharge.doubleValue();
		log.info("standardCharge:{} " , standardCharge);
		log.info("toUSDStandardCharge:{} " , toUSDStandardCharge);
		log.info("toUSDStandardChargeDouble:{} " , toUSDStandardChargeDouble);
		//暫訂最低收美金7元 (台幣200)，最高收美金28元(台幣800)
		if (toUSDStandardChargeDouble < 7) {
			toUSDStandardCharge = BigDecimal.valueOf(7);
			standardCharge = BigDecimal.valueOf(7).divide(toUSDfxRate);
		} 
		else if (toUSDStandardChargeDouble > 28) {
			toUSDStandardCharge = BigDecimal.valueOf(28);
			standardCharge = BigDecimal.valueOf(28).divide(toUSDfxRate);
		}
		return standardCharge;
	}
	
	//傳入外匯編號，執行匯入解款
	public void settle(IRSaveCmd irSaveCmd) {
		IRMaster irmaster = IRMasterRepository.findByIrNo(irSaveCmd.getIrNo());
		irSaveCmd.setPaidStats("2");
		BeanUtils.copyProperties(irSaveCmd,irmaster);
		IRMasterRepository.save(irmaster);
	}
}
