package com.jiayu.gamana.resource.dao;

import com.jiayu.gamana.resource.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    User findUserById(long id);

    User findUserByUP(@Param("username") String username, @Param("password") String password);

    void insertUser(User user);

    List<User> selectAllUser();

    void updateByUUID(User user);

    boolean deleteByUUID(String uuid);
}
