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
public class TableResponseDTO implements Serializable {


	private static final long serialVersionUID = 178350965533565933L;

	private long id;

	private String tablename;

	private String newtime;

	private String edittime;

	private String appid;

	private String type;

	private String typename;

	private int fieldnum;

	
}
