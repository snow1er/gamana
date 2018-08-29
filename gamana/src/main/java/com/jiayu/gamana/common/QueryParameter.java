package com.jiayu.gamana.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 * This class wraps query conditions from url
 * @author Neo.Li
 */
public class QueryParameter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4129453143523142579L;

	@Context
	private UriInfo uriInfo;
	
	public MultivaluedMap<String, String> getParameters() {
		MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();
		if (uriInfo !=null && uriInfo.getQueryParameters() != null) {
			map.putAll(uriInfo.getQueryParameters());
		}
		return map;
	}
	
	public Map<String, String> getFirstParameter() {
		Map<String, String> map = new HashMap<String, String>();
		if (uriInfo !=null && uriInfo.getQueryParameters() != null) {
			uriInfo.getQueryParameters().keySet().stream().forEach(key -> map.put(key, uriInfo.getQueryParameters().getFirst(key)));
		}
		return map;
	}
}
