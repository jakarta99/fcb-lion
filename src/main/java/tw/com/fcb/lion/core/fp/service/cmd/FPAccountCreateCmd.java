package tw.com.fcb.lion.core.fp.service.cmd;

import lombok.Data;

@Data
public class FPAccountCreateCmd {

	private String accountNo;
	
	private String customerIdno;
}