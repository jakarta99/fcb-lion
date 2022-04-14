package tw.com.fcb.lion.core.fp.web.dto;

import lombok.Data;

@Data
public class FPCusterDto {

	private Long id;
	
	private String fpcAccount;
	
	private String fpcCustomerId;

	private String fpcStatus;

	private String fpcValidCrcyCnt;
}
