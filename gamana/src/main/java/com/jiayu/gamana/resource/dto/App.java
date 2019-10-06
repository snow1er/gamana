package com.jiayu.gamana.resource.dto;

import com.jiayu.gamana.util.DateUtil;
import com.jiayu.gamana.util.MD5Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.security.provider.MD5;

import java.io.Serializable;

/**
 * The Data Transfer Object is used to operate user.
 * @author Carrie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class App implements Serializable {


	private static final long serialVersionUID = -6513562500015561131L;

	private long id;

	private long createuser;

	private String appname;
	
	private String appid;

	private String newtime;

	private String edittime;

	public App (String appname, int createuser) {
		this.createuser = createuser;
		String time = DateUtil.getCurrentDateStr();
		this.appname = appname;
		this.appid = MD5Util.encrypt(appname + time);
		this.newtime = time;
		this.edittime = time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreateuser() {
		return createuser;
	}

	public void setCreateuser(long createuser) {
		this.createuser = createuser;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getNewtime() {
		return newtime;
	}

	public void setNewtime(String newtime) {
		this.newtime = newtime;
	}

	public String getEdittime() {
		return edittime;
	}

	public void setEdittime(String edittime) {
		this.edittime = edittime;
	}

	@Override
	public String toString() {
		return "App{" +
				"appname='" + appname + '\'' +
				", appid='" + appid + '\'' +
				", newtime='" + newtime + '\'' +
				", edittime='" + edittime + '\'' +
				'}';
	}
}
