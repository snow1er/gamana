package com.jiayu.gamana.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The Data Transfer Object is used to operate user.
 * @author Neo.Li
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesBaseDTO implements Serializable {


	private static final long serialVersionUID = 6513877076481995986L;

	private long tableid;

	private String newtime;

	private String edittime;

	private String field;

	private String filedname;




}
