package com.jiayu.gamana.resource.service.impl;

import java.util.List;
import java.util.UUID;

import com.jiayu.gamana.resource.dao.UserMapper;
import com.jiayu.gamana.resource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiayu.gamana.resource.dto.User;

/**
 * Implement <code>UserService</code>
 * @author Carrie
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;


	@Override
	public void createUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public User getByUserId(long id) {
		return userMapper.findUserById(id);
	}


	@Override
	public User login(String username, String password) {
		User user = userMapper.findUserByUP(username,password);
		if (user != null)
			return user;
		else
			return null;
	}

	@Override
	public boolean editUser(User user) {
		userMapper.updateByUUID(user);
		return true;
	}

	@Override
	public List<User> getList() {
		return userMapper.selectAllUser();
	}

	@Override
	public boolean deleteUser(String uuid) {
		return userMapper.deleteByUUID(uuid);
	}
}
