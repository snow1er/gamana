package com.jiayu.gamana.message;

import org.springframework.http.HttpStatus;

/**
 * Define message code.
 * The message code includes error code and http status.
 * The error code is three category number and five index number.
 * The category is from {@linkplain enum MessageCategory}}
 * @author Neo.Li
 */
public enum MessageCode {

	// Common error code definition, start with 001
	AUTHENTICATION_FAILED(0x00100001, HttpStatus.UNAUTHORIZED),
	INVALID_PARAM(0x00100002, HttpStatus.BAD_REQUEST),
	AUTHENTICATION_METHOD_NOT_SUPPORT(0x00100003, HttpStatus.UNAUTHORIZED),
	AUTHENTICATION_USERNAME_NOT_FOUND(0x00100004, HttpStatus.UNAUTHORIZED),
	
	// User error code definition, start with 002
	USER_LOGIN_INVALID(0x00200001, HttpStatus.UNAUTHORIZED);
	
	MessageCode(int code, HttpStatus status){
		this.code = code;
		this.status =status;
	}
	
	enum MessageCategory
	{
		COMMON		    ( 1 ),
		USER		    ( 2 );
		
		MessageCategory(int value){
			
		}
	}
	private int code;
	private HttpStatus status;
	

	public int getCode() {
		return code;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
	public String getCodeString(){
		return String.format("%08X", code & 0xFFFFFFFF);
	}
		
}
