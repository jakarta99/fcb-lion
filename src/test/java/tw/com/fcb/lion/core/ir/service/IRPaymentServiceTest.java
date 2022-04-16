package tw.com.fcb.lion.core.ir.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IRPaymentServiceTest {

	
	@Autowired
	IRPaymentService irPaymentService;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	void calculateFeeTest() {
		//測試<=7元
		//Arrange
		BigDecimal irAmt1 = new BigDecimal(1000);
		BigDecimal expectValue1 = new BigDecimal(7);
		String ccy1 = "USD";
		//Act
		BigDecimal actualValue1 = irPaymentService.calculateOriginalCurrencyFee(irAmt1, ccy1);
		//Assert
		assertEquals(expectValue1, actualValue1);
		log.info("actualValue1: {}",actualValue1);
		log.info("expectValue1: {}",expectValue1);
		//測試>=28元
		//Arrange
		BigDecimal irAmt2 = new BigDecimal(200000);
		BigDecimal expectValue2 = new BigDecimal(28);
		String ccy2 = "USD";
		//Act
		BigDecimal actualValue2 = irPaymentService.calculateOriginalCurrencyFee(irAmt2, ccy2);
		//Assert
		assertEquals(expectValue2, actualValue2);
		log.info("actualValue2: {}",actualValue2);
		log.info("expectValue2: {}",expectValue2);
		
	}

	@Test
	void calculateTWDFeeTest() {
		//測試<=200元
		//Arrange
		BigDecimal irAmt1 = new BigDecimal(1000);
		BigDecimal expectValue1 = new BigDecimal(200);
		String ccy1 = "JPY";
		//Act
		BigDecimal actualValue1 = irPaymentService.calculateTWDFee(irAmt1, ccy1);
		//Assert
		assertEquals(expectValue1, actualValue1);
		log.info("actualValue1: {}",actualValue1);
		log.info("expectValue1: {}",expectValue1);
		//測試>=800元
		//Arrange
		BigDecimal irAmt2 = new BigDecimal(10000000);
		BigDecimal expectValue2 = new BigDecimal(800);
		String ccy2 = "JPY";
		//Act
		BigDecimal actualValue2 = irPaymentService.calculateTWDFee(irAmt2, ccy2);
		//Assert
		assertEquals(expectValue2, actualValue2);
		log.info("actualValue2: {}",actualValue2);
		log.info("expectValue2: {}",expectValue2);

	}
}
