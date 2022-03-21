package tw.com.fcb.lion.core.ir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.lion.core.ir.repository.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	Optional<Customer> findByCustomerId(String customerId);
}
