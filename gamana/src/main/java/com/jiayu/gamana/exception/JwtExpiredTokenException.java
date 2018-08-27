package com.jiayu.gamana.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Define exception for Jwt token expired
 * @author Neo.Li
 */
public class JwtExpiredTokenException extends AuthenticationException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3872064430247950703L;

	public JwtExpiredTokenException(String msg) {
        this(msg, null);
    }

    public JwtExpiredTokenException(String msg, Throwable t) {
        super(msg, t);
    }

}