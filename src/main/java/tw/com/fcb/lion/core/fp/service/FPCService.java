package tw.com.fcb.lion.core.fp.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.fcb.lion.core.fp.service.cmd.FPAccountCreateCmd;
import tw.com.fcb.lion.core.fp.service.mapper.FpAccountVoMapper;
import tw.com.fcb.lion.core.fp.service.vo.FPAccountVo;
import tw.com.fcb.lion.core.ir.repository.FPCustomerRepository;
import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;
import tw.com.fcb.lion.core.ir.repository.entity.FPMaster;

@Transactional
@Service
public class FPCService {

	@Autowired
	FPCustomerRepository fPCustomerRepository;
	
	@Autowired
	FpAccountVoMapper fpAccountVoMapper;
	Logger log = LoggerFactory.getLogger(getClass());
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

	// 傳入帳號，查詢帳號、幣別資訊
	public FPCuster getByfpcAccount(String acc) {
		FPCuster fpcuster = fPCustomerRepository.findByfpcAccount(acc);
		return fpcuster;
	}

	// 傳入帳號、幣別，查詢餘額
	public BigDecimal getByfpmCurrencyBal(String acc, String crcy) {
		BigDecimal accfpmBal = null;
		FPCuster fpcuster = fPCustomerRepository.findByfpcAccount(acc);
		List<FPMaster> fpmArry = fpcuster.getFpmasters();
		for (FPMaster fpMaster : fpmArry) {
			if (fpMaster.getFpmCurrency().equals(crcy)) {
				accfpmBal = fpMaster.getFpmBalance();
			}
		}
		return accfpmBal;
	}

	// 傳入帳號、幣別，查詢幣別資訊
	public FPMaster getByfpmCurrencyData(String acc, String crcy) {
		FPMaster fPMaster = null;
		FPCuster fpcuster = fPCustomerRepository.findByfpcAccount(acc);
		List<FPMaster> fpmArry = fpcuster.getFpmasters();
		for (FPMaster idx : fpmArry) {
			if (idx.getFpmCurrency().equals(crcy)) {
				fPMaster = idx;
			}
		}
		return fPMaster;
	}

	// 更新幣別餘額
	public FPCuster updfpmBal(String acc, String crcy, BigDecimal addAmt, BigDecimal subAmt) {
		FPMaster fPMaster = null;
		FPCuster fpcuster = fPCustomerRepository.findByfpcAccount(acc);
		List<FPMaster> fpmArry = fpcuster.getFpmasters();
		for (FPMaster idx : fpmArry) {
			if (idx.getFpmCurrency().equals(crcy)) {
				fPMaster = idx;
				BigDecimal afterBal = idx.getFpmBalance().add(addAmt).subtract(subAmt);
				fPMaster.setFpmBalance(afterBal);
			}
		}
		return fpcuster;
	}

	// 新增帳號、幣別資訊
	public FPAccountVo create(FPAccountCreateCmd createCmd) {

//		FPCuster fpcentity = new FPCuster();
//			List<FPMaster> fpmArry = fpcentity.getFpmasters();
//			fpmArry.add(crcyData);

//		BeanUtils.copyProperties(createCmd, fpcentity);
		FPCuster fpcentity = fpAccountVoMapper.toEntity(createCmd);
		fPCustomerRepository.save(fpcentity);
		log.info("fpcentity: {}" , fpcentity);
		
		FPAccountVo vo = fpAccountVoMapper.toVo(fpcentity);
//		FPAccountVo vo = new FPAccountVo();
//		vo.setId(fpcentity.getId());
//		vo.setAccountNo(fpcentity.getFpcAccount());
//		vo.setCustomerIdno(fpcentity.getFpcCustomerId());
		log.info("FPAccountVo: {}" , vo);
		//vo.setStatus(fpcentity.getFpcStatus());
		
		
		
		
		return vo;
	}

}
