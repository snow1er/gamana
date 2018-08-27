package com.jiayu.gamana.user.dto;

import java.io.Serializable;
import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Serializable{

	private static final long serialVersionUID = 3084886935034986718L;

	private UUID userId;
	
    private String username;
	
	private String password;
}
