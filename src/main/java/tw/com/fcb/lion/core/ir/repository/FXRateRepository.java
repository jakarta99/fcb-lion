package tw.com.fcb.lion.core.ir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.lion.core.ir.repository.entity.FXRateVo;

@Repository
public interface FXRateRepository extends JpaRepository<FXRateVo, Long>{
	FXRateVo findByCurrency(String currency);
}
