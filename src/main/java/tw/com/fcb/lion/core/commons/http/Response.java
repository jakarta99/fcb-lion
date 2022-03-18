package tw.com.fcb.lion.core.commons.http;

import lombok.Data;
import tw.com.fcb.lion.core.commons.enums.ResponseStatus;

@Data
public class Response<T> {

	ResponseStatus status;
	
	String code;
	
	String message;
	
	T data;
	
}
