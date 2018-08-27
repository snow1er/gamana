package com.jiayu.gamana.security.provider;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.jiayu.gamana.security.dto.AuthContext;
import com.jiayu.gamana.user.dto.UserResponseDTO;
import com.jiayu.gamana.user.service.UserService;

/**
 * auth for username and password
 * @author Neo.Li
 */
@Component
public class PreAuthenticationProvider implements AuthenticationProvider {

	@Autowired
    private UserService userService;
	
	/**
	 * valid username and password
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        try {
        	UserResponseDTO user = userService.login(username, password);
        	return new UsernamePasswordAuthenticationToken(new AuthContext(user.getUserId(), user.getUsername(), UUID.randomUUID().toString()), null, null);
        }catch(Exception e) {
        	throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.equals(authentication));
	}

}
