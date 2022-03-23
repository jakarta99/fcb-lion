package tw.com.fcb.lion.core.commons.http;

import lombok.Data;
import tw.com.fcb.lion.core.commons.enums.ResponseStatus;

@Data
public class Response<T> {

	ResponseStatus status;
	
	String code;
	
	String message;
	
	T data;
	
	public void showMessage(T data, String code, String message){
		
		if(code.equals("0000")) {
			this.setStatus(ResponseStatus.SUCCESS);
			this.setCode(code);
			this.setMessage(message);
			this.setData(data);
		}
		else {
			this.setStatus(ResponseStatus.ERROR);
			this.setCode(code);
			this.setMessage(message);
			this.setData(data);
		}
	}
}
