package com.jiayu.gamana.resource.service.impl;

import com.jiayu.gamana.resource.dao.AppMapper;
import com.jiayu.gamana.resource.dto.App;
import com.jiayu.gamana.resource.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implement <code>UserService</code>
 * @author Carrie
 */
@Service
public class AppServiceImpl implements AppService {

	@Autowired
	AppMapper appMapper;

	@Override
	public boolean createApp(App app) {
		appMapper.insertApp(app);
		return true;
	}

	@Override
	public App getById(long id) {
		return appMapper.findAppById(id);
	}

	@Override
	public App getByAppId(String appid) {
		return appMapper.findAppByAppidId(appid);
	}

	@Override
	public List<App> getList() {
		return appMapper.selectAllApp();
	}

	@Override
	public List<App> getListByCreateuser(long createuser) {
		return appMapper.findAppByCreateuser(createuser);
	}

	@Override
	public boolean editApp(App app) {
		appMapper.updateByAppId(app);
		return true;
	}

	@Override
	public boolean deleteApp(String appid) {
		appMapper.deleteByAppId(appid);
		return true;
	}
}
