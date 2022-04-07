package tw.com.fcb.lion.core.sharedkernel.api;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;

@FeignClient(name = "updfpc", url = "http://localhost:8080")
public interface FPClient {

	@Operation(summary="更新幣別FPM餘額")
	@GetMapping("/fpc-upd/{account}/{crcy}/balance")
	FPCuster updfpmBal(@PathVariable("account") String acc,
			@PathVariable("crcy") String crcy,
			@RequestParam BigDecimal addAmt, 
			@RequestParam BigDecimal subAmt);
}
