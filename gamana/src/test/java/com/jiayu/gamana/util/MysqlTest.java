package com.jiayu.gamana.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import com.jiayu.gamana.resource.dto.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Test method for StringUtils
 * @author Neo.Li
 */
public class MysqlTest {

	@Test
	public void testUser() {
		//读取配置文件
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//初始化mybatis，创建SqlSessionFactory类实例
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		//创建Session实例
		SqlSession session = sqlSessionFactory.openSession();
		User user = session.selectOne("com.jiayu.gamana.resource.dao.UserMapper.findUserById",1);
		System.out.println(user.toString());
		session.commit();
		//关闭Session
		session.close();
	}

}
