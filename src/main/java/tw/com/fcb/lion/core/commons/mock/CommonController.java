package tw.com.fcb.lion.core.commons.mock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Mock", description = "Mock API")
@RestController
@RequestMapping("/common")
public class CommonController {

	@Operation(summary = "判斷受通知分行(含是否為本行帳號檢查)")
	@GetMapping("/BeAdvisingBranch")
	public Boolean isBeAdvisingBranch(@RequestParam("beneficiaryAccount") String beneficiaryAccount) {
			return true;
	}
	@Operation(summary = "判斷所屬存匯行")
	@GetMapping("/DepositBank")
	public String getDepositBank(@RequestParam("senderSwiftCode") String senderSwiftCode) {
		return "CITIUS33XXX";
	}
	@Operation(summary = "讀取銀行檔")
	@GetMapping("/BankNameAndAddress")
	public String getBankNameAndAddress(@RequestParam("bankSwiftCode") String bankSwiftCode) {
		return "CITI BANK                          U.S.A NY";
	}
	
	@Operation(summary = "判斷轉匯案件")
	@GetMapping("/RemittanceTransfer")
	public Boolean isRemittanceTransfer(@RequestParam("accountInstitution") String accountInstitution) {
		return false;
	}
	
}
