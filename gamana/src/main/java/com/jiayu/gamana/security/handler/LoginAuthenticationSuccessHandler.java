package com.jiayu.gamana.security.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiayu.gamana.base.dto.ResponseData;
import com.jiayu.gamana.common.Constants;
import com.jiayu.gamana.security.JwtUtil;
import com.jiayu.gamana.security.dto.AuthContext;


/**
 * login success handler
 * @author Neo.Li
 */
@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private static final Logger logger = LoggerFactory.getLogger(LoginAuthenticationSuccessHandler.class);
	
	@Autowired
    protected JwtUtil jwtUtil;
	
	@Autowired
    protected ObjectMapper mapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.debug("User successfully authenticated.");
    	Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put(Constants.TOKEN, generateToken(authentication));
        tokenMap.put(Constants.REFRESH_TOKEN, generateRefreshToken(authentication));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResponseData re = new ResponseData(HttpStatus.OK.value(), tokenMap);
        mapper.writeValue(response.getWriter(), re);
        clearAuthenticationAttributes(request);
	}

	private String generateToken(Authentication authentication) {
		AuthContext authContext = (AuthContext) authentication.getPrincipal();
    	return jwtUtil.generateToken(authContext);
    }
    
    /**
     * generate refresh token
     * @param authentication
     * @return
     */
    private String generateRefreshToken(Authentication authentication) {
    	AuthContext authContext = (AuthContext) authentication.getPrincipal();
    	return jwtUtil.generateRefreshToken(authContext);
    }
    
    /**
     * Removing attribute from http session if present.
     * @param request
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
