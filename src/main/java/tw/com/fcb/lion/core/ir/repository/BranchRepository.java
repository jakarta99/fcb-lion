package tw.com.fcb.lion.core.ir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.lion.core.ir.repository.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
	Branch findByBranchCode(String branchCode);
}
