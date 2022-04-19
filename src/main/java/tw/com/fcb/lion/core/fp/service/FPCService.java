package tw.com.fcb.lion.core.fp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.fcb.lion.core.fp.respository.FPCustomerRepository;
import tw.com.fcb.lion.core.fp.respository.entity.FPCuster;
import tw.com.fcb.lion.core.fp.respository.entity.FPMaster;
import tw.com.fcb.lion.core.fp.service.cmd.FPAccountCreateCmd;
import tw.com.fcb.lion.core.fp.service.mapper.FpAccountVoMapper;
import tw.com.fcb.lion.core.fp.service.vo.FPAccountVo;
import tw.com.fcb.lion.core.fp.web.request.FPAccountCreateRequest;

@Transactional
@Service
public class FPCService {

	@Autowired
	FPCustomerRepository fPCustomerRepository;
	
	@Autowired
	FpAccountVoMapper fpAccountVoMapper;
	Logger log = LoggerFactory.getLogger(getClass());

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

	// 新增帳號資訊
	public FPAccountVo createFpc(FPAccountCreateCmd createCmd) {
		log.info("createCmd: {}" , createCmd);

		FPCuster fpcentity = fpAccountVoMapper.toEntity(createCmd);
		fpcentity.setFpcStatus("00");
		fpcentity.setValidCrcyCnt(0);
//		fpAccountVoMapper等同以下註解!!!
// 		FPCuster fpcentity = new FPCuster();
//		fpcentity.setFpcAccount(createCmd.getAccountNo());
//		fpcentity.setFpcCustomerId(createCmd.getCustomerIdno());
//		fpcentity.setBookType(String.valueOf(createCmd.getBookType()));
		fPCustomerRepository.save(fpcentity);
		log.info("fpcentity: {}" , fpcentity);

		FPAccountVo vo = fpAccountVoMapper.toVo(fpcentity);
//		fpAccountVoMapper等同以下註解!!!
//		FPAccountVo vo = new FPAccountVo();
//		vo.setId(fpcentity.getId());
//		vo.setAccountNo(fpcentity.getFpcAccount());
//		vo.setCustomerIdno(fpcentity.getFpcCustomerId());
//		vo.setStatus(fpcentity.getFpcStatus());
//		vo.setValidCrcyCnt(fpcentity.getFpcValidCrcyCnt());
		log.info("FPAccountVo: {}" , vo);
		
		return vo;
	}

	// 新增幣別資訊
		public FPAccountVo createFpm(FPAccountCreateCmd createCmd) {
			log.info("createCmd: {}" , createCmd);
			FPCuster fpcuster = fPCustomerRepository.findByfpcAccount(createCmd.getAccountNo());
			FPMaster fpMaster  = new FPMaster();
			fpMaster.setFpmCurrency(String.valueOf(createCmd.getCrcyCode()));
			fpMaster.setFpmStatus("00");
			fpMaster.setFpmBalance(new BigDecimal(0.00));
			
			if(fpcuster.getFpmasters() == null) {
				List<FPMaster> fpmArry = new ArrayList<FPMaster>();
				fpmArry.add(fpMaster);
				fpcuster.setFpmasters(fpmArry);
			}else {
//*********	會出現null 待研究
				List<FPMaster> fpmArry = fpcuster.getFpmasters();
				fpmArry.add(fpMaster);
				fpcuster.setFpmasters(fpmArry);
			}
			
			fpcuster.setValidCrcyCnt(fpcuster.getValidCrcyCnt() + 1 );
			fPCustomerRepository.save(fpcuster);
			log.info("fpcentity: {}" , fpcuster);

			FPAccountVo vo = fpAccountVoMapper.toVo(fpcuster);
			log.info("FPAccountVo: {}" , vo);

			return vo;
		}

}
