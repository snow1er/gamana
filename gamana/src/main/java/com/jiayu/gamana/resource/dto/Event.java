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
public class Event implements Serializable {


	private static final long serialVersionUID = 6628087577921671848L;

	private long id;

	private String appid;

	private String event;

	private String event_alias;

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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEvent_alias() {
		return event_alias;
	}

	public void setEvent_alias(String event_alias) {
		this.event_alias = event_alias;
	}

	public long getCreateuser() {
		return createuser;
	}

	public void setCreateuser(long createuser) {
		this.createuser = createuser;
	}

	@Override
	public String toString() {
		return "Event{" +
				"id=" + id +
				", appid='" + appid + '\'' +
				", event='" + event + '\'' +
				", event_alias='" + event_alias + '\'' +
				", newtime='" + newtime + '\'' +
				", edittime='" + edittime + '\'' +
				", createuser=" + createuser +
				'}';
	}
}
