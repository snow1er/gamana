package com.jiayu.gamana.user;

import java.util.UUID;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiayu.gamana.user.dto.UserBaseDTO;
import com.jiayu.gamana.user.dto.UserResponseDTO;
import com.jiayu.gamana.user.service.UserService;

/**
 * Provide restful API regarding to user
 * @author Neo.Li
 */
@Component
@Path("/users")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {

	@Autowired
    private UserService userService;
	
	@POST
    @Path("/register")
    public UserResponseDTO registerUser(@Valid UserBaseDTO user) {
		return userService.createUser(user);
    }
	
	@GET
    @Path("/{id}")
    public UserResponseDTO getUser(@PathParam("id") String userId) {
		return userService.getByUserId(UUID.fromString(userId));
    }
	
}
