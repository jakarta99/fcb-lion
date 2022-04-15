package tw.com.fcb.lion.core.fp.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tw.com.fcb.lion.core.fp.common.enums.Status;

@Data
@Schema(description = "外存帳號")
// DTO Data Transfer Object
public class FPAccountDto {

	@Schema(description = "id")
	private Long id;
	
	@Schema(description = "外存帳號")
	private String accountNo;

	@Schema(description = "客戶統編")
	private String customerIdno;

	@Schema(description = "外存狀態")
	private Status status;

	@Schema(description = "有效帳號數")
	private Integer validCrcyCnt;
}
