package com.jiayu.gamana.message;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class GamanaMessagesImpl implements IGamanaMessages {
	
	@Autowired
	private MessageSource messageSource;
	
    private MessageSourceAccessor accessor;
	
	
	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource);
	}
	
	@Override
	public String getMessage(String code) {
		return accessor.getMessage(code);
	}

	@Override
	public String getMessage(String code, Locale locale) {
		return accessor.getMessage(code, locale);
	}

	@Override
	public String getMessage(String code, Locale locale, String defaultMessage) {
		return accessor.getMessage(code, defaultMessage, locale);
	}
	
	@Override
	public String getMessage(String code, Object[] args) {
		return accessor.getMessage(code, args);
	}

	@Override
	public String getMessage(String code, Object[] args, Locale locale) {
		return accessor.getMessage(code, args, locale);
	}
	
	@Override
	public String getMessage(String code, Object[] args, Locale locale, String defaultMessage) {
		return accessor.getMessage(code, args, defaultMessage, locale);
	}

}
