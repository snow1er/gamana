package com.jiayu.gamana.message;

import java.util.Locale;

/**
 * This interface will query  message from property files.
 * @author Neo.Li
 */
public interface IGamanaMessages {

	/**
	 * Retrieve the message for the given code and the default Locale.
	 * @param code
	 * @return
	 */
	String getMessage(String code);
	
	/**
	 * Retrieve the message for the given code and the given Locale.
	 * @param code
	 * @param locale
	 * @return
	 */
	String getMessage(String code, Locale locale);
	
	/**
	 * Retrieve the message for the given code and the given Locale.
	 * If the message not found, return the given default message.
	 * @param code
	 * @param locale
	 * @param defaultMessage
	 * @return
	 */
	String getMessage(String code, Locale locale, String defaultMessage);
	
	/**
	 * Retrieve the message for the given code and the default Locale.
	 * @param code
	 * @param args
	 * @return
	 */
	String getMessage(String code, Object[] args);
	
	/**
	 * Retrieve the message for the given code and the given Locale.
	 * @param code
	 * @param args
	 * @param locale
	 * @return
	 */
	String getMessage(String code, Object[] args, Locale locale);
	
	/**
	 * Retrieve the message for the given code and the given Locale.
	 * If the message not found, return the given default message.
	 * @param code
	 * @param args
	 * @param locale
	 * @param defaultMessage
	 * @return
	 */
	String getMessage(String code, Object[] args, Locale locale, String defaultMessage);
	
}
