package com.mall.jiuzhenbao.base.exception;

import com.mall.jiuzhenbao.message.SpogMessageCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

/**
 * A general service exception
 * 
 * @author Depeng.Liu
 *
 */
public class SpogServiceException extends AuthenticationException {
	private static final long serialVersionUID = -5743772636851118376L;
	
	private String code;
	private HttpStatus httpStatus;
	private Object[] arguments;
	
	public SpogServiceException(SpogMessageCode messageCode, Object... args) {
		this(messageCode.getStatus(), messageCode.getCodeString(), args);
	}
	
	public SpogServiceException(String message, Object... args) {
		this(HttpStatus.INTERNAL_SERVER_ERROR, message, args);
	}
	
	public SpogServiceException(Throwable e, String message, Object... args) {
		this(HttpStatus.INTERNAL_SERVER_ERROR, e, message, args);
	}
	
	public SpogServiceException(HttpStatus httpStatus, String message, Object... args) {
		this(httpStatus, null, message, args);
	}
	
	public SpogServiceException(HttpStatus httpStatus, Throwable e, String message, Object... args) {
		super(message, e);
		this.code = message;
		this.httpStatus = httpStatus;
		this.arguments = args;
	}

	public String getCode() {
		return code;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public Object[] getArguments() {
		return arguments;
	}
}
