package com.jiayu.gamana.security.provider;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.jiayu.gamana.common.Constants;
import com.jiayu.gamana.security.JwtAuthenticationToken;
import com.jiayu.gamana.security.JwtUtil;
import com.jiayu.gamana.security.dto.AuthContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * auth for those apis will validate token
 * @author Neo.Li
 */
@Component
public class PostAuthenticationProvider implements AuthenticationProvider {

	@Autowired
    protected JwtUtil jwtUtil;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		JwtAuthenticationToken authToken = (JwtAuthenticationToken) authentication;
        Claims claims = validateAccessToken(authToken.getToken());
        UUID userId = getUserId(claims);
        String userName = getUserName(claims);
        String roleId = getRoleId(claims);
        AuthContext authContext = new AuthContext(userId, userName, roleId);
		Set<GrantedAuthority> accesses=new HashSet<GrantedAuthority>();
		accesses.add(new SimpleGrantedAuthority(authContext.getRoleId()));
        return new JwtAuthenticationToken(authContext, null, accesses);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.equals(authentication));
	}

	/**
	 * valid token
	 * @param authToken
	 * @return
	 */
	private Claims validateAccessToken(String authToken) {
        Jws<Claims> jwsClaims = jwtUtil.parseClaims(authToken);
        return jwsClaims.getBody();
    }
	
	/**
	 * get user id from token
	 * @param claims
	 * @return
	 */
	private UUID getUserId(Claims claims) {
        return UUID.fromString((String) claims.get(Constants.USER_ID));
    }
	
	/**
	 * get user name from token
	 * @param claims
	 * @return
	 */
	private String getUserName(Claims claims) {
        return (String) claims.get(Constants.USER_NAME);
    }
	
	/**
	 * get role id from token
	 * @param claims
	 * @return
	 */
	private String getRoleId(Claims claims) {
        return (String) claims.get(Constants.ROLE_ID);
    }
}
