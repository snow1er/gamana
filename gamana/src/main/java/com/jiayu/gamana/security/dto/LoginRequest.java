package com.jiayu.gamana.security.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@JsonProperty(value = "username", required = true)
    private String username;
	
	@JsonProperty(value = "password", required = true)
    private String password;
	
}