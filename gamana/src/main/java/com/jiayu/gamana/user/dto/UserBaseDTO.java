package com.jiayu.gamana.user.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Data Transfer Object is used to operate user.
 * @author Neo.Li
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseDTO implements Serializable {

	private static final long serialVersionUID = -7322057704562301966L;

	private String username;
	
	private String password;
	
}
