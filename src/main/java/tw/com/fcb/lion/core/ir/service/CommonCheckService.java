package tw.com.fcb.lion.core.ir.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.fcb.lion.core.ir.repository.BranchRepository;
import tw.com.fcb.lion.core.ir.repository.CustomerRepository;
import tw.com.fcb.lion.core.ir.repository.FXRateRepository;
import tw.com.fcb.lion.core.ir.repository.entity.Branch;
import tw.com.fcb.lion.core.ir.repository.entity.Customer;
import tw.com.fcb.lion.core.ir.repository.entity.FxRate;

@Transactional
@Service
public class CommonCheckService {
	
	@Autowired
	FXRateRepository fxRateRepository;
	
	@Autowired
	BranchRepository branchRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	public FxRate checkCurrency(String currencyCode) throws Exception {
		FxRate fxRate = fxRateRepository.findByCurrency(currencyCode);
		
		if(fxRate == null) {
			throw new Exception("幣別輸入錯誤");
		}
		else {
			return fxRate;
		}
	}
	
	public void checkBranchCode(String branchCode) throws Exception {
		Branch branch = branchRepository.findByBranchCode(branchCode);
		
		if(branch == null) {
			throw new Exception("分行輸入錯誤");
		}
	}
	
	public void checkCustomerId(String customerId) throws Exception {
		Customer customer = customerRepository.findByCustomerId(customerId);
		
		if(customer == null) {
			throw new Exception("統編輸入錯誤");
		}
	}
}
