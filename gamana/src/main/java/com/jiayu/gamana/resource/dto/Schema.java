package com.jiayu.gamana.resource.dto;

import com.jiayu.gamana.util.DateUtil;
import com.jiayu.gamana.util.MD5Util;
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
public class Schema implements Serializable {


	private static final long serialVersionUID = 4066934897490789960L;

	private long id;

	private String appid;

	private String xwhat;

	private String schema;

	private String newtime;

	private String edittime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getXwhat() {
		return xwhat;
	}

	public void setXwhat(String xwhat) {
		this.xwhat = xwhat;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
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
		return "Schema{" +
				"id=" + id +
				", appid='" + appid + '\'' +
				", xwhat='" + xwhat + '\'' +
				", schema='" + schema + '\'' +
				", newtime='" + newtime + '\'' +
				", edittime='" + edittime + '\'' +
				'}';
	}
}
