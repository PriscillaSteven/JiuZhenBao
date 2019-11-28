package com.mall.jiuzhenbao.message;

/**
 * Define internal exception bean
 * It will be as response entity.
 * @author Neo.Li
 */
public class SpogErrorMessageBean {

	// Internal error code
	private String code;
	
	//Related error message. It need implements internationalization
	private String message;
	
	//More detail message.
	private String detailMessage;
	
	public SpogErrorMessageBean() {
		this("","","");
	}
	
	public SpogErrorMessageBean(String code, String message){
		this(code, message, "");
	}
	public SpogErrorMessageBean(String code, String message, String detailMessage){
		this.code = code;
		this.message = message;
		this.detailMessage = detailMessage;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	public String getDetailMessage() {
		return detailMessage;
	}
	
	@Override
	public String toString() {
		return " code : " + this.code
				+ " message : " + this.message
				+ " detailMessage : " + this.detailMessage;
	}
}
