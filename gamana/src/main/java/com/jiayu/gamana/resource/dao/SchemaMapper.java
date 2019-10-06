package com.jiayu.gamana.resource.dao;

import com.jiayu.gamana.resource.dto.App;
import com.jiayu.gamana.resource.dto.Event;
import com.jiayu.gamana.resource.dto.Field;
import com.jiayu.gamana.resource.dto.Schema;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SchemaMapper {

    List<Schema> findByAppid(String appid);

    Schema findByAppidXwhat(@Param("appid") String appid, @Param("xwhat") String xwhat);

    List<String> findXwhatByAppid(String appid);

    List<String> findFieldByAppid(String appid);

    List<Event> findEventMapByAppid(String appid);

    List<Field> findFieldMapByAppid(String appid);

    List<String> findAppid();

    List<App> findTableApp();
}
