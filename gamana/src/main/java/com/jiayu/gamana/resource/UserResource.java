package com.jiayu.gamana.resource;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiayu.gamana.resource.dto.User;
import com.jiayu.gamana.resource.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Provide restful API regarding to user
 * @author Carrie
 */
@Component
@Path("/users")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {

	@Autowired
    private UserService userService;

	
	@GET
    @Path("/{id}")
    public User getUser(@PathParam("id") long id) {
		return userService.getByUserId(id);
    }

    @POST
    @Path("/create")
    public User createUser(@RequestBody User user) {
	    user.setUuid(UUID.randomUUID().toString());
        System.out.println(user.toString());
	    userService.createUser(user);

	    return null;
    }


    @POST
    @Path("/edit")
    public boolean editUser(@RequestBody User user) {
        System.out.println(user.toString());
        return userService.editUser(user);
    }


    @POST
    @Path("/delete")
    public boolean deleteUser(@RequestBody User user) {
        System.out.println(user.toString());
        return userService.deleteUser(user.getUuid());
    }

    @GET
    @Path("findall")
    public List<User> getUser() {
        return userService.getList();
    }
	
}
