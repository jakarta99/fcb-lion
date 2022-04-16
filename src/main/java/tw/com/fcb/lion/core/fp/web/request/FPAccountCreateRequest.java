package tw.com.fcb.lion.core.fp.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tw.com.fcb.lion.core.fp.common.enums.BookType;
import tw.com.fcb.lion.core.fp.common.enums.CrcyCode;
import tw.com.fcb.lion.core.fp.common.enums.Status;

@Data
public class FPAccountCreateRequest {

	@Schema(description = "外活帳號account")
	private String accountNo;
	
	@Schema(description = "客戶統編customer's idno")
	private String customerIdno;
	
	@Schema(description = "存戶狀態")
	private Status status;

	@Schema(description = "有效幣別數")
	private Integer validCrcyCnt;
	
	@Schema(description = "幣別")
	private CrcyCode crcyCode;
	
	@Schema(description = "存摺種類")
	private BookType bookType;
	

}
