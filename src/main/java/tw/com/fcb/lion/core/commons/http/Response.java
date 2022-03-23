package tw.com.fcb.lion.core.commons.http;

import lombok.Data;
import tw.com.fcb.lion.core.commons.enums.ResponseStatus;
import tw.com.fcb.lion.core.ir.web.dto.IR;

@Data
public class Response<T> {

	ResponseStatus status;
	
	String code;
	
	String message;
	
	T data;
	
	public void showMessage(T data, String code, String msg){
		
		if(code.equals("0000")) {
			this.setStatus(ResponseStatus.SUCCESS);
			this.setCode(code);
			this.setMessage(msg);
			this.setData(data);
		}
		else {
			this.setStatus(ResponseStatus.ERROR);
			this.setCode(code);
			this.setMessage(msg);
			this.setData(data);
		}
	}
}
