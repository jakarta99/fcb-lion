package tw.com.fcb.lion.core.fp.web.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FPTxLogQueryRequest {

	private String account;
	
	private LocalDate txDate;
	
	private String crcy;
	
}
