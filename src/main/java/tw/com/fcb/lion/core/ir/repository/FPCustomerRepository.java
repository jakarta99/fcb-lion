package tw.com.fcb.lion.core.ir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;
import tw.com.fcb.lion.core.ir.repository.entity.FPMaster;
import tw.com.fcb.lion.core.ir.repository.entity.IRSwiftMessage;

@Repository
public interface FPCustomerRepository extends JpaRepository<FPCuster , Long>{
	
	FPMaster findByfpcAccount(String acc);
	FPMaster getByfpcAccount(IRSwiftMessage irSwiftMessage);

}
