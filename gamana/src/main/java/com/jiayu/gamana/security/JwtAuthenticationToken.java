package com.jiayu.gamana.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;

/**
 * {@link org.springframework.security.core.Authentication }wrapper for string jwt token
 * @author Neo.Li
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -5801432268789199884L;
	
	private String token;

	public JwtAuthenticationToken(final String token) {
		super(new Principal(){

			@Override
			public String getName() {
				return token;
			}
			
		}, token);
		this.token = token;
	}


	public JwtAuthenticationToken(Object principal,Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal,credentials,authorities);
		this.eraseCredentials();
	}

	public String getToken() {
		return token;
	}

	@Override
	public String getName() {
		return token;
	}
	
}
