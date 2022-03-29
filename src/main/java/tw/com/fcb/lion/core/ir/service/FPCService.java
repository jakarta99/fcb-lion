package tw.com.fcb.lion.core.ir.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.fcb.lion.core.ir.repository.FPCustomerRepository;
import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;
import tw.com.fcb.lion.core.ir.repository.entity.FPMaster;
import tw.com.fcb.lion.core.ir.repository.entity.IRSwiftMessage;

@Transactional
@Service
public class FPCService {

	@Autowired
	FPCustomerRepository FPCustomerRepository;
	
	public void insert(FPCuster fPCuster) {
		FPCuster entity = new FPCuster();
		BeanUtils.copyProperties(fPCuster, entity);
		FPCustomerRepository.save(entity);
	}
	
	//傳入IRSwiftMessage 的 帳號、幣別查詢內容
		public FPMaster getByfpcAccount(IRSwiftMessage irSwiftMessage) {
			System.out.println("xxxx = " + irSwiftMessage.getBeneficiaryAccount());
			FPMaster fpcuster = FPCustomerRepository.findByfpcAccount(irSwiftMessage.getBeneficiaryAccount());
			
			System.out.println("xxxx = " + fpcuster);
			return fpcuster;
		}
		
		//傳入ID查詢內容
//		public IRSwiftMessage getBySwiftMessageId(Long id) {
//			IRSwiftMessage irSwiftMessage = repository.findById(id).orElseThrow( () -> new RuntimeException("errID") );
//
//			return irSwiftMessage;
//		}
}
