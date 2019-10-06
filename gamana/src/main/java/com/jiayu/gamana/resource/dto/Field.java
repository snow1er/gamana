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
public class Field implements Serializable {


	private static final long serialVersionUID = 6628087577921671848L;

	private long id;

	private String appid;

	private String field;

	private String field_alias;

	private String newtime;

	private String edittime;

	private long createuser;

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

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getField_alias() {
		return field_alias;
	}

	public void setField_alias(String field_alias) {
		this.field_alias = field_alias;
	}

	public long getCreateuser() {
		return createuser;
	}

	public void setCreateuser(long createuser) {
		this.createuser = createuser;
	}

	@Override
	public String toString() {
		return "Field{" +
				"id=" + id +
				", appid='" + appid + '\'' +
				", field='" + field + '\'' +
				", field_alias='" + field_alias + '\'' +
				", newtime='" + newtime + '\'' +
				", edittime='" + edittime + '\'' +
				", createuser=" + createuser +
				'}';
	}
}
