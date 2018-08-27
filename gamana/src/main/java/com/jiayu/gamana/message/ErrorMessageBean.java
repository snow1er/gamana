package com.jiayu.gamana.message;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageBean implements Serializable{

	private static final long serialVersionUID = -2403753890716932800L;

	// Internal error code
	private String code;
		
	//Related error message. It need implements internationalization
	private String message;
		
	//More detail message.
	private String detailMessage;
	
	public ErrorMessageBean(String code) {
		this.code = code;
	}
	
	public ErrorMessageBean(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
