package tw.com.fcb.lion.core.commons.mock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Mock", description = "Mock API")
@RestController
@RequestMapping("/common")
public class CommonController {

	@Operation(summary = "判斷受通知分行(含是否為本行帳號檢查)")
	@GetMapping("/BeAdvisingBranch")
	public Boolean getBeAdvisingBranch(Long id) {
		return true;
	}
	@Operation(summary = "判斷所屬存匯行")
	@GetMapping("/DepositBank")
	public Boolean getDepositBank(Long id) {
		return true;
	}
	@Operation(summary = "讀取銀行檔")
	@GetMapping("/BankNameAndAddress")
	public Boolean getBankNameAndAddress(Long id) {
		return true;
	}
	
	@Operation(summary = "判斷轉匯案件")
	@GetMapping("/RemittanceTransfer")
	public Boolean IsRemittanceTransfer(Long id) {
		return true;
	}
	
}
