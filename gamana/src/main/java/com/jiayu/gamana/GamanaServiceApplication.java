package com.jiayu.gamana;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Service start on this point.
 * @author Neo.Li
 */
@SpringBootApplication(scanBasePackages={"com.jiayu.gamana.*"})
@MapperScan("com.jiayu.gamana.resource.dao")
public class GamanaServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(GamanaServiceApplication.class,args);
	}

}
