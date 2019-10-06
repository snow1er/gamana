package com.jiayu.gamana.resource.dao;

import com.jiayu.gamana.resource.dto.App;
import com.jiayu.gamana.resource.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppMapper {

    App findAppById(long id);

    List<App> findAppByCreateuser(long createuser);

    App findAppByAppidId(String appId);

    App findAppByname(String appname);

    void insertApp(App app);

    List<App> selectAllApp();

    void updateByAppId(App app);

    boolean deleteByAppId(String appid);
}
