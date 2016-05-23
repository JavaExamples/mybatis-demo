package cn.demo.mybatis.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.demo.mybatis.mapper.UserMapper;
import cn.demo.mybatis.po.OrderCustom;
import cn.demo.mybatis.po.Orders;
import cn.demo.mybatis.po.User;

public class CacheTest {

	// 会话工厂
	private SqlSessionFactory sqlSessionFactory;

	// 创建工厂
	@Before
	public void init() throws IOException {

		// 配置文件（SqlMapConfig.xml）
		String resource = "SqlMapConfig.xml";

		// 加载配置文件到输入 流
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

	}
	//一级缓存
	@Test
	public void testCache1() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//第一次查询用户id为1的用户
		User user = userMapper.findUserById(1);
		System.out.println(user);
		
		//中间修改用户要清空缓存，目的防止查询出脏数据
		/*user.setUsername("测试用户2");
		userMapper.updateUser(user);
		sqlSession.commit();*/
		
		//第二次查询用户id为1的用户
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		
		sqlSession.close();
		

	}
	
	//二级缓存的测试
	@Test
	public void testCache2() throws Exception {

		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		
		//第一次查询用户id为1的用户
		User user = userMapper1.findUserById(1);
		System.out.println(user);
		sqlSession1.close();
		
		//中间修改用户要清空缓存，目的防止查询出脏数据
		/*user.setUsername("测试用户2");
		userMapper3.updateUser(user);
		sqlSession3.commit();
		sqlSession3.close();*/
		
		
		//第二次查询用户id为1的用户
		User user2 = userMapper2.findUserById(1);
		System.out.println(user2);
		
		sqlSession2.close();
		

	}

	
}
