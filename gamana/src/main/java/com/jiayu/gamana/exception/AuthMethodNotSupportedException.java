package com.jiayu.gamana.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Define exception for /login  api  request with type other than  post.
 * @author Neo.Li
 */
public class AuthMethodNotSupportedException extends AuthenticationException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1814072335904646972L;

	public AuthMethodNotSupportedException(String msg) {
        super(msg);
    }
}