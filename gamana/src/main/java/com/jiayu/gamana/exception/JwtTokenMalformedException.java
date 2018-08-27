package com.jiayu.gamana.exception;

/**
 * Define exception for invalid Jwt token
 * @author Neo.Li
 */
public class JwtTokenMalformedException extends RuntimeException {



	/**
	 * 
	 */
	private static final long serialVersionUID = 3089459335213619207L;

	public JwtTokenMalformedException(String string) {
		super(string);
	}

}
