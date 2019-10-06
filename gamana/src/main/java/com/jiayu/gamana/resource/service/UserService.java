package com.jiayu.gamana.resource.service;

import java.util.List;
import java.util.UUID;

import com.jiayu.gamana.resource.dto.User;

/**
 * Service contract for User.
 * @author Carrie
 */
public interface UserService {

	void createUser(User user);

	
	/**
	 * get user by user id
	 * @param id
	 * @return
	 */
	User getByUserId(long id);
	
	/**
	 * login via username and password
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

	boolean editUser(User user);


	List<User> getList();

	boolean deleteUser(String uuid);
}
