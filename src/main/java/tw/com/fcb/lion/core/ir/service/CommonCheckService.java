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
	BranchRepository branchRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	FXRateRepository fxRateRepository;
	
	public Branch checkBranchCode(String branchCode) throws Exception {
		return branchRepository.findByBranchCode(branchCode).orElseThrow(() -> new RuntimeException("分行輸入錯誤"));
	}
	
	public void updateBranchSerialNo(Branch branch) {
		Integer branchSerialNo = Integer.valueOf(branch.getBranchSerialNo()) + 1;
		branch.setBranchSerialNo(branchSerialNo);
		branchRepository.save(branch);
	}
	
	public Customer checkCustomerId(String customerId) throws Exception {
		return customerRepository.findByCustomerId(customerId).orElseThrow(() -> new RuntimeException("統編輸入錯誤"));
	}
	
	public FxRate checkCurrency(String currencyCode) throws Exception {
		return fxRateRepository.findByCurrency(currencyCode).orElseThrow(() -> new RuntimeException("幣別輸入錯誤"));
	}
}
