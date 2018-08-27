package com.jiayu.gamana.user.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jiayu.gamana.user.dto.UserBaseDTO;
import com.jiayu.gamana.user.dto.UserResponseDTO;
import com.jiayu.gamana.user.service.UserService;

/**
 * Implement <code>UserService</code>
 * @author Neo.Li
 */
@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserResponseDTO createUser(UserBaseDTO userBaseDTO) {
		return mackUser();
	}

	@Override
	public UserResponseDTO getByUserId(UUID userId) {
		return mackUser();
	}

	private UserResponseDTO mackUser() {
		return new UserResponseDTO(UUID.randomUUID(), "new user", "123456");
	}

	@Override
	public UserResponseDTO login(String username, String password) {
		return new UserResponseDTO(UUID.randomUUID(), username, password);
	}
}
