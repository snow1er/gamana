package com.jiayu.gamana.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiayu.gamana.base.dto.ResponseData;
import com.jiayu.gamana.message.ErrorMessageBean;
import com.jiayu.gamana.message.MessageCode;


@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        int httpStatusValue = HttpStatus.UNAUTHORIZED.value();
        String errorCode = MessageCode.AUTHENTICATION_FAILED.getCodeString();
        response.setStatus(httpStatusValue);
        ErrorMessageBean messageBean = new ErrorMessageBean(errorCode, authException.getMessage());
        ResponseData re = new ResponseData();
        re.setStatus(httpStatusValue);
        re.setErrors(new ErrorMessageBean[] {messageBean});
        re.setData("");
        objectMapper.writeValue(response.getWriter(), re);
        
		
	}

}
