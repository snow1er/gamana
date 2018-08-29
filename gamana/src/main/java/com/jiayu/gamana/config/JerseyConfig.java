package com.jiayu.gamana.config;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import com.jiayu.gamana.report.ReportResource;
import com.jiayu.gamana.user.UserResource;

import ch.qos.logback.core.net.SocketConnector.ExceptionHandler;

/**
 * Jersey restful service configuration
 * @author Neo.Li
 */
@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
		registerAPIResources();
		register(ExceptionHandler.class); // handle exception
	}

	/**
	 * publish api.
	 */
	private void registerAPIResources() {
		register(UserResource.class);
		register(ReportResource.class);
	}
}