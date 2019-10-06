package com.jiayu.gamana.resource.service;



import com.jiayu.gamana.resource.dto.App;

import java.util.List;

/**
 * Service contract for User.
 * @author Carrie
 */
public interface AppService {

	/**
	 * create app
	 * @param app
	 * @return
	 */
	boolean createApp(App app);
	
	/**
	 * get app by id
	 * @param id
	 * @return
	 */
	App getById(long id);

	App getByAppId(String appid);


	List<App> getList();

	List<App> getListByCreateuser(long createuser);


	boolean editApp(App appBaseDTO);

	boolean deleteApp(String appid);
	

}
