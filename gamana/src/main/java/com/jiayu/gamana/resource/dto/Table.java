package com.jiayu.gamana.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The Data Transfer Object is used to operate user.
 * @author Carrie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table implements Serializable {


	private static final long serialVersionUID = 5110482054972390811L;


	private String appid;

	private String appname;

	private int eventcount;

	private int fieldcount;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public int getEventcount() {
		return eventcount;
	}

	public void setEventcount(int eventcount) {
		this.eventcount = eventcount;
	}

	public int getFieldcount() {
		return fieldcount;
	}

	public void setFieldcount(int fieldcount) {
		this.fieldcount = fieldcount;
	}

	@Override
	public String toString() {
		return "Table{" +
				"appid='" + appid + '\'' +
				", appname='" + appname + '\'' +
				", eventcount=" + eventcount +
				", fieldcount=" + fieldcount +
				'}';
	}
}
