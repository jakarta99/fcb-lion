package tw.com.fcb.lion.core.fp.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FPAccountCreateRequest {

	@Schema(description = "account")
	private String accountNo;
	
	@Schema(description = "customer's idno")
	private String customerIdno;
	

}
