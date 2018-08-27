package com.jiayu.gamana.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	
	@JsonProperty(value="token", required=true)
	private String token;
	
	@JsonProperty(value="refresh_token", required=true)
	private String refreshToken;

}
