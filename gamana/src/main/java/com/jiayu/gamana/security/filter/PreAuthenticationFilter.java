package com.jiayu.gamana.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiayu.gamana.exception.AuthMethodNotSupportedException;
import com.jiayu.gamana.security.dto.LoginRequest;
import com.jiayu.gamana.util.StringUtils;

/**
 * This filter will be called when client calls /login api.
 * @author Neo.Li
 */
public class PreAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
    private final AuthenticationSuccessHandler successHandler;
    
    private final AuthenticationFailureHandler failureHandler;

    private final ObjectMapper objectMapper;

    public PreAuthenticationFilter(String defaultProcessUrl, AuthenticationSuccessHandler successHandler,
                                   AuthenticationFailureHandler failureHandler, ObjectMapper mapper) {
        super(defaultProcessUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.objectMapper = mapper;
    }

    /**
     * check login user
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (!HttpMethod.POST.name().equals(request.getMethod())) {
            throw new AuthMethodNotSupportedException("Authentication method not supported");
        }

        LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return this.getAuthenticationManager().authenticate(token);
    }

    /**
     * handle valid user
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    /**
     * handle invalid user
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
