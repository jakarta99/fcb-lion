package tw.com.fcb.lion.core.fp.service.cmd;

import lombok.Data;
import tw.com.fcb.lion.core.fp.common.enums.Status;

@Data
public class FPAccountCreateCmd {

	private String accountNo;
	
	private String customerIdno;

	private String status;

	private Integer validCrcyCnt;
}
