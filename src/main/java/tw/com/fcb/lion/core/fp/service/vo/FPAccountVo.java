package tw.com.fcb.lion.core.fp.service.vo;

import lombok.Data;
import tw.com.fcb.lion.core.fp.common.enums.Status;

// Value object 
@Data
public class FPAccountVo {

	private Long id;
	
	private String accountNo;
	
	private String customerIdno;
	
	private Status status;

	private Integer validCrcyCnt;

}
