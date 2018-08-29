package com.jiayu.gamana.report;

import java.util.List;
import java.util.Map;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiayu.gamana.common.QueryParameter;
import com.jiayu.gamana.report.service.ReportService;

/**
 * Provide restful API regarding to report
 * @author Neo.Li
 */
@Component
@Path("/report")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ReportResource {

	@Autowired
    private ReportService reportService;
	
	/**
	 * get report data
	 * URL like : http://localhost:8080/api/report/track/active/7777777?startdate=2018-01-01&enddate=2018-12-01&datatype=list
	 * @param platform
	 * @param name
	 * @param appid
	 * @param parameter
	 * @return
	 */
	@GET
    @Path("/{platform}/{name}/{appid}")
    public Map<String, List<?>> getReportData(@PathParam("platform") String platform
    		, @PathParam("name") String name
    		, @PathParam("appid") String appid, @BeanParam QueryParameter parameter) {
		return reportService.getReportData(platform, appid, name, parameter.getFirstParameter(), 0);
    }
	
	/**
	 * generate sql
	 * URL like : http://localhost:8080/api/report/track/active/7777777/sql?startdate=2018-01-01&enddate=2018-12-01&datatype=list
	 * @param platform
	 * @param name
	 * @param appid
	 * @param parameter
	 * @return
	 */
    @POST
    @Path("/{platform}/{name}/{appid}/sql")
    public String generateSql(@PathParam("platform") String platform
    		, @PathParam("name") String name
    		, @PathParam("appid") String appid, @BeanParam QueryParameter parameter){
        return reportService.generateSql(appid, name, platform, parameter.getFirstParameter());
    }
}
