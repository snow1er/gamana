package com.jiayu.gamana.user.service;

import java.util.UUID;

import com.jiayu.gamana.user.dto.UserBaseDTO;
import com.jiayu.gamana.user.dto.UserResponseDTO;

/**
 * Service contract for User.
 * @author Neo.Li
 */
public interface UserService {

	/**
	 * create user
	 * @param userBaseDTO
	 * @return
	 */
	UserResponseDTO createUser(UserBaseDTO userBaseDTO);
	
	/**
	 * get user by user id
	 * @param userId
	 * @return
	 */
	UserResponseDTO getByUserId(UUID userId);
	
	/**
	 * login via username and password
	 * @param username
	 * @param password
	 * @return
	 */
	UserResponseDTO login(String username, String password);
}
