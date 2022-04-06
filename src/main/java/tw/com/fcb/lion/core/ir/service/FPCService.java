package tw.com.fcb.lion.core.ir.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.fcb.lion.core.ir.repository.FPCustomerRepository;
import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;
import tw.com.fcb.lion.core.ir.repository.entity.FPMaster;

@Transactional
@Service
public class FPCService {

	@Autowired
	FPCustomerRepository FPCustomerRepository;
	
	
//	新增帳號
//	public void insert(FPCuster fPCuster) {
//		FPCuster entity = new FPCuster();
//		BeanUtils.copyProperties(fPCuster, entity);
//		FPCustomerRepository.save(entity);
//	}
	
//	新增幣別 xxx
//	public void insert(FPMaster fPMaster) {
//		FPMaster entity = new FPMaster();
//		BeanUtils.copyProperties(fPMaster, entity);
//		FPMasterRepository.save(entity);
//	}
	
	//傳入帳號，查詢帳號、幣別資訊
	public FPCuster getByfpcAccount(String acc) {
		FPCuster fpcuster = FPCustomerRepository.findByfpcAccount(acc);
		return fpcuster;
	}
	
	//傳入帳號、幣別，查詢餘額
	public BigDecimal getByfpmCurrencyBal(String acc,String crcy) {
		BigDecimal accfpmBal = null;
		FPCuster fpcuster = FPCustomerRepository.findByfpcAccount(acc);
		List<FPMaster> fpmArry = fpcuster.getFpmasters();
		for (FPMaster fpMaster : fpmArry) {
			if (fpMaster.getFpmCurrency().equals(crcy)) {
				accfpmBal = fpMaster.getFpmBalance();
			}
		}
		return accfpmBal;
	}
	
	//傳入帳號、幣別，查詢幣別資訊
		public FPMaster getByfpmCurrencyData(String acc,String crcy) {
			FPMaster fPMaster = null;
			FPCuster fpcuster = FPCustomerRepository.findByfpcAccount(acc);
			List<FPMaster> fpmArry = fpcuster.getFpmasters();
			for (FPMaster idx : fpmArry) {
				if (idx.getFpmCurrency().equals(crcy)) {
					fPMaster = idx;
				}
			}
			return fPMaster;
		}
		
	//更新幣別餘額
		public FPCuster updfpmBal(String acc,String crcy,BigDecimal addAmt,BigDecimal subAmt) {
			FPMaster fPMaster = null;
			FPCuster fpcuster = FPCustomerRepository.findByfpcAccount(acc);
			List<FPMaster> fpmArry = fpcuster.getFpmasters();
			for (FPMaster idx : fpmArry) {
				if (idx.getFpmCurrency().equals(crcy)) {
					fPMaster = idx;
					BigDecimal afterBal = idx.getFpmBalance().add(addAmt).subtract(subAmt);
					fPMaster.setFpmBalance(afterBal);
				}
			}
			System.out.println("test@@@ = " + fPMaster);
			return fpcuster;
		}	
		
		
	//新增帳號、幣別資訊
		public FPCuster insfpcAccount(FPCuster accData) {
			
			FPCuster fpcentity = new FPCuster();
//			List<FPMaster> fpmArry = fpcentity.getFpmasters();
//			fpmArry.add(crcyData);
			System.out.println("test@@@= " + accData);
			BeanUtils.copyProperties(accData, fpcentity);
			FPCustomerRepository.save(fpcentity);
			return fpcentity;
		}
	
}
