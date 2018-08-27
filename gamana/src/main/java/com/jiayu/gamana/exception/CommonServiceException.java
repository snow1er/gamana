package com.jiayu.gamana.exception;

/**
 * Define common exception
 * @author Neo.Li
 */
public class CommonServiceException extends RuntimeException{

	private static final long serialVersionUID = 4152192976025075414L;

	private String code;
	private Object[] arguments;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object[] getArguments() {
		return arguments;
	}
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}
	
}
