package tw.com.fcb.lion.core.ir.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.lion.core.fp.service.FPCService;
import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;
import tw.com.fcb.lion.core.ir.repository.entity.FPMaster;

@SpringBootTest
class FPCServiceTest {

	@Autowired
	FPCService fpCService;
	
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	void updfpmBalTest() {
		//Arrange
		String acc="09340123456";
		String crcy="USD";
		BigDecimal beforeBalance= fpCService.getByfpmCurrencyBal(acc,crcy);
		BigDecimal addAmt = new BigDecimal(1000);
		BigDecimal subAmt = new BigDecimal(100);
		//Act
		FPCuster fpCuster = fpCService.updfpmBal(acc, crcy, addAmt, subAmt);
		BigDecimal expectValue = beforeBalance.add(addAmt).subtract(subAmt);
		//Assert
		for( FPMaster fpMaster : fpCuster.getFpmasters()) {
			if(fpMaster.getFpmCurrency().equals("USD")) {
				BigDecimal actualValue = fpMaster.getFpmBalance();
				assertEquals(expectValue, actualValue);
				log.info("actualValue: {}",actualValue);
				log.info("expectValue: {}",expectValue);
			}
		}
			
		
	}

}
