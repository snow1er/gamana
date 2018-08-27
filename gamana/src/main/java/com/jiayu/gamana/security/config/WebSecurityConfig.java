package com.jiayu.gamana.security.config;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiayu.gamana.security.RequestURLMatcher;
import com.jiayu.gamana.security.filter.PostAuthenticationFilter;
import com.jiayu.gamana.security.filter.PreAuthenticationFilter;
import com.jiayu.gamana.security.filter.RestAuthenticationEntryPoint;
import com.jiayu.gamana.security.handler.LoginAuthenticationFailureHandler;
import com.jiayu.gamana.security.handler.LoginAuthenticationSuccessHandler;
import com.jiayu.gamana.security.provider.PostAuthenticationProvider;
import com.jiayu.gamana.security.provider.PreAuthenticationProvider;

/**
 * Web security configuration
 * @author Neo.Li
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // user login path
    public static final String AUTHENTICATE_LOGIN_PATH = "/api/users/login";
    
    public static final String AUTHENTICATE_REGISTER_PATH = "/api/users/register";
    
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/**";
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private PostAuthenticationProvider postAuthenticationProvider;

    @Autowired
    private PreAuthenticationProvider preAuthenticationProvider;
    
    @Autowired
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;
    
    @Autowired
    private LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    /**
     * Set web security
     * 
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(this.authenticationEntryPoint)
            .and()
            .headers().frameOptions().sameOrigin()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(AUTHENTICATE_REGISTER_PATH).permitAll()
            .antMatchers("/**").authenticated()//.permitAll()
            .and()
            .addFilterBefore(preAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(postAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
    }
    
    /**
     * Authentication filter for login path, it will check user information
     * @return
     */
    private PreAuthenticationFilter preAuthenticationFilter() {
        PreAuthenticationFilter filter = new PreAuthenticationFilter(AUTHENTICATE_LOGIN_PATH, 
        		loginAuthenticationSuccessHandler, loginAuthenticationFailureHandler, objectMapper);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    /**
     * Authentication filter for others path except login path, it will check token from request header
     * @return
     */
    private PostAuthenticationFilter postAuthenticationFilter() {
        PostAuthenticationFilter filter = new PostAuthenticationFilter(loginAuthenticationFailureHandler);
        filter.setAuthenticationManager(authenticationManager);
        filter.setRequiresAuthenticationRequestMatcher(requestMatcher());
        return filter;
    }

	private RequestMatcher requestMatcher() {
		List<String> whitePathList = Arrays.asList(AUTHENTICATE_LOGIN_PATH, AUTHENTICATE_REGISTER_PATH);
        RequestMatcher rm = new RequestURLMatcher(whitePathList, TOKEN_BASED_AUTH_ENTRY_POINT);
        return rm;

	}
    
    /**
     * Configure authentication providers
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider((AuthenticationProvider) preAuthenticationProvider)
        	.authenticationProvider((AuthenticationProvider) postAuthenticationProvider);
    }

    /**
     * If remove the below code, console will print the below message.
     * Using default security password: {}
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
