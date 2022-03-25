package tw.com.fcb.lion.core.ir.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class IRMasterRepositoryTest {

	@Autowired
	IRMasterRepository irMasterRepository;

	@Test
	void findByBeAdvisingBranchAndPrintAdvisingMk() {
		Integer caseCunt = irMasterRepository.findByBeAdvisingBranchAndPrintAdvisingMk("094","N").size();
		assertEquals(caseCunt,3);
	}
	
	
}
