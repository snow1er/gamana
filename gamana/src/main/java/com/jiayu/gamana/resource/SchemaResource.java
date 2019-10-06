package com.jiayu.gamana.resource;

import com.jiayu.gamana.resource.dao.SchemaMapper;
import com.jiayu.gamana.resource.dto.*;
import com.jiayu.gamana.resource.service.AppService;
import com.jiayu.gamana.resource.service.SchemaService;
import com.jiayu.gamana.util.DateUtil;
import com.jiayu.gamana.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Provide restful API regarding to user
 * @author Carrie
 */
@Component
@Path("/schemas")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class SchemaResource {

	@Autowired
    private SchemaService schemaService;

	@Autowired
    SchemaMapper schemaMapper;

	
	@GET
    @Path("getSchemas/{appid}")
    public List<Schema> getSchemas(@PathParam("appid") String appid) {
		return schemaService.getByAppId(appid);
    }

    @GET
    @Path("getevents/{appid}")
    public List<Event> getEvents(@PathParam("appid") String appid) {
        return schemaService.getEventsByAppid(appid);
    }

    @GET
    @Path("getfields/{appid}")
    public List<Field> getFields(@PathParam("appid") String appid) {
        return schemaService.getFieldByAppid(appid);
    }

    @GET
    @Path("getcolumntables")
    public List<Table> getColumnTables() {
	    //这里需要根据登陆用户的权限app获取，目前先把所有app返回

        return schemaService.findTables();
    }

//    @GET
//    @Path("getSchema")
//    public Schema getByAppidXwhat(@RequestParam("appid") String appid, @RequestParam("xwhat") String xwhat) {
//        return schemaService.getByAppidXwhat(appid, xwhat);
//    }

}
