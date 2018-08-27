package com.jiayu.gamana.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiayu.gamana.base.dto.ResponseData;
import com.jiayu.gamana.exception.AuthMethodNotSupportedException;
import com.jiayu.gamana.message.ErrorMessageBean;
import com.jiayu.gamana.message.MessageCode;

/**
 * login failed handler
 * @author Neo.Li
 */
@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory.getLogger(LoginAuthenticationFailureHandler.class);
	
	private final ObjectMapper mapper;
	
	@Autowired
    public LoginAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info("Authentication failed!");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorMessageBean messageBean;
        if(exception instanceof AuthMethodNotSupportedException){
            messageBean = new ErrorMessageBean(MessageCode.AUTHENTICATION_METHOD_NOT_SUPPORT.getCodeString(), exception.getMessage());
        }else if(exception instanceof UsernameNotFoundException){
            messageBean = new ErrorMessageBean(MessageCode.AUTHENTICATION_USERNAME_NOT_FOUND.getCodeString(), exception.getMessage());
        }else{
            messageBean = new ErrorMessageBean(MessageCode.AUTHENTICATION_FAILED.getCodeString(), exception.getMessage());
        }
        ResponseData re = new ResponseData();
        re.setStatus(HttpStatus.UNAUTHORIZED.value());
        re.setErrors(new ErrorMessageBean[] {messageBean});
        re.setData("");
        mapper.writeValue(response.getWriter(), re);
		
	}

}
