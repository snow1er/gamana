package com.jiayu.gamana.security.filter;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.ObjectUtils;

import com.jiayu.gamana.common.Constants;
import com.jiayu.gamana.security.JwtAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * This filter will be called for /api/** url pattern.
 * and check for jwt token in request header
 * @author Neo.Li
 */
public class PostAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private final AuthenticationFailureHandler failureHandler;

    public PostAuthenticationFilter(AuthenticationFailureHandler failureHandler) {
	    super("/**");
	    this.failureHandler = failureHandler;
    }
    
    /**
     * check token
     */
    @Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
        JwtAuthenticationToken authRequest;
        String tokenPayload = extractToken(request.getHeader(Constants.AUTH_HEADER_NAME));
        authRequest = new JwtAuthenticationToken(tokenPayload);
        return getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * handler valid token
     */
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain, Authentication authResult)
			throws IOException, ServletException {
		if (authResult.getPrincipal() != null) {
			SecurityContext context = SecurityContextHolder.createEmptyContext();
			context.setAuthentication(authResult);
			SecurityContextHolder.setContext(context);
		}
		chain.doFilter(request, response);
	}

    /**
     * handle invalid token
     */
    @Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }


    /**
     *
     * Get token from request header. The format is Bearer {tokenstr}.
     * Then extract this token, remove profix.
     * @param tokenHeader
     * @return
     */
    public String extractToken(String tokenHeader) {
        if (ObjectUtils.isEmpty(tokenHeader)) {
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }
        if (!tokenHeader.startsWith(Constants.HEADER_PREFIX)) {
            throw new AuthenticationServiceException("Invalid authorization header.");
        }
        return tokenHeader.substring(Constants.HEADER_PREFIX.length(), tokenHeader.length());
    }
}