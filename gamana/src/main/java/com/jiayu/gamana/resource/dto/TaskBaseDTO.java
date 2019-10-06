package com.jiayu.gamana.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * The Data Transfer Object is used to operate user.
 * @author Neo.Li
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskBaseDTO implements Serializable {


	private static final long serialVersionUID = 735599260772239927L;

	private String taskname;

	private String newtime;

	private String edittime;

	private String desc;

	private List<String> appidlist;

	private String sql;

	private String elementjson;

	private String status;
	
}
