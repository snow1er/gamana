package com.jiayu.gamana.resource;

import com.jiayu.gamana.resource.dto.App;
import com.jiayu.gamana.resource.dto.User;
import com.jiayu.gamana.resource.service.AppService;
import com.jiayu.gamana.resource.service.UserService;
import com.jiayu.gamana.util.DateUtil;
import com.jiayu.gamana.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

/**
 * Provide restful API regarding to user
 * @author Carrie
 */
@Component
@Path("/apps")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AppResource {

	@Autowired
    private AppService appService;

	
	@GET
    @Path("/{id}")
    public App getApp(@PathParam("id") long id) {
		return appService.getById(id);
    }

    @POST
    @Path("/create")
    public App createApp(@RequestBody App app) {
	    String time = DateUtil.getCurrentDateStr();
	    String appid = MD5Util.encrypt(app.getAppname() + time );
	    app.setAppid(appid);
	    app.setNewtime(time);
	    app.setEdittime(time);
        System.out.println(app.toString());
	    appService.createApp(app);

	    return appService.getByAppId(appid);
    }


    @POST
    @Path("/edit")
    public boolean editApp(@RequestBody App app) {
        System.out.println(app.toString());
        app.setEdittime(DateUtil.getCurrentDateStr());
        return appService.editApp(app);
    }


    @POST
    @Path("/delete")
    public boolean deleteApp(@RequestBody App app) {
        System.out.println(app.toString());
        return appService.deleteApp(app.getAppid());
    }

    @GET
    @Path("findall")
    public List<App> getApp() {
        return appService.getList();
    }


    @GET
    @Path("findall/{createuser}")
    public List<App> getAppByCreateuser(@PathParam("createuser") long createuser) {
        return appService.getListByCreateuser(createuser);
    }
	
}
