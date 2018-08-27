package com.jiayu.gamana.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class RequestURLMatcher implements RequestMatcher {

	private OrRequestMatcher blackmatcher;
	private OrRequestMatcher whitematcher;
	
	private final static RequestMatcher anymatcher=AnyRequestMatcher.INSTANCE;
	private final static RequestMatcher nonematcher=new NegatedRequestMatcher(AnyRequestMatcher.INSTANCE);
	
	public RequestURLMatcher(List<String> pathsToSkip, String processingPath) {
		this(pathsToSkip.toArray(new String[] {}), new String[] { processingPath });
	}

	public RequestURLMatcher(String[] whitePathList, String[] blackPathList) {
		List<RequestMatcher> whiteList = new ArrayList<>();
		if(whitePathList.length==0)whiteList.add(nonematcher);
		for (String path : whitePathList) {
			whiteList.add(new AntPathRequestMatcher(path));
		}
		List<RequestMatcher> blackList = new ArrayList<>();
		if(blackPathList.length==0)blackList.add(anymatcher);
		for (String path : blackPathList) {
			blackList.add(new AntPathRequestMatcher(path));
		}

		whitematcher = new OrRequestMatcher(whiteList);
		blackmatcher = new OrRequestMatcher(blackList);
	}
	
	@Override
	public boolean matches(HttpServletRequest request) {
		if (whitematcher.matches(request)) {
			return false;
		}
		return blackmatcher.matches(request) ? true : false;
	}

}
