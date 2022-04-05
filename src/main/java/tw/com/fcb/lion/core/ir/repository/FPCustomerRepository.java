package tw.com.fcb.lion.core.ir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;



@Repository
public interface FPCustomerRepository extends JpaRepository<FPCuster , Long>{
	FPCuster findByfpcAccount(String acc);

}
