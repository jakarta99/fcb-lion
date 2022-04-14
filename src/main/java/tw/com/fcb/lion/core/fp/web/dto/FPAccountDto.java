package tw.com.fcb.lion.core.fp.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tw.com.fcb.lion.core.fp.common.enums.Status;

@Data
@Schema(description = "xxxx")
// DTO Data Transfer Object
public class FPAccountDto {

	@Schema(description = "xxxx")
	private Long id;
	
	@Schema(description = "xxxx")
	private String accountNo;
	
	private String customerIdno;

	private Status status;

	private Integer validCrcyCnt;
}
