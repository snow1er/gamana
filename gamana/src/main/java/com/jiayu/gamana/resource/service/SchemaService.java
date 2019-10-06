package com.jiayu.gamana.resource.service;



import com.jiayu.gamana.resource.dto.*;

import java.util.List;
import java.util.Map;

/**
 * Service contract for User.
 * @author Carrie
 */
public interface SchemaService {


	List<Schema> getByAppId(String appid);


	Schema getByAppidXwhat(String appid, String xwhat);

	List<Field> getFieldByAppid(String appid);

	List<Event> getEventsByAppid(String appid);

	List<Table> findTables();

}
