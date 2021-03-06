package tw.com.fcb.lion.core.ir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.lion.core.ir.repository.entity.FxRate;

@Repository
public interface FXRateRepository extends JpaRepository<FxRate, Long>{
	Optional<FxRate> findByCurrency(String currency);
}
