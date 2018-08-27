package com.jiayu.gamana.base.dto;

import java.io.Serializable;

import com.jiayu.gamana.message.ErrorMessageBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData implements Serializable{

	private static final long serialVersionUID = -1863352866897399332L;

	private int status;
	
    private Object data;
    
    private ErrorMessageBean[] errors = new ErrorMessageBean[0];
    
    public ResponseData(int status, Object data) {
    	this.status = status;
    	this.data = data;
    }
}
