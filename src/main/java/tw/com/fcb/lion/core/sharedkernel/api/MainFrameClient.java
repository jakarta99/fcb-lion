package tw.com.fcb.lion.core.sharedkernel.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;

@FeignClient(name = "verify", url = "http://localhost:8080")
public interface MainFrameClient {

	@Operation(summary = "判斷受通知分行(含是否為本行帳號檢查)")
	@GetMapping("/common/BeAdvisingBranch")
	Boolean isBeAdvisingBranch(@RequestParam("beneficiaryAccount") String beneficiaryAccount);

	@Operation(summary = "判斷所屬存匯行")
	@GetMapping("/common/DepositBank")
	String getDepositBank(@RequestParam("senderSwiftCode") String senderSwiftCode);
	
	@Operation(summary = "讀取銀行檔")
	@GetMapping("/common/BankNameAndAddress")
	String getBankNameAndAddress(@RequestParam("bankSwiftCode") String bankSwiftCode);
	
	@Operation(summary = "判斷轉匯案件")
	@GetMapping("/common/RemittanceTransfer")
	Boolean isRemittanceTransfer(@RequestParam("accountInstitution") String accountInstitution);
	

}
