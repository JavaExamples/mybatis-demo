package cn.demo.mybatis.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.demo.mybatis.mapper.UserMapper;
import cn.demo.mybatis.po.User;
import cn.demo.mybatis.po.UserCustom;
import cn.demo.mybatis.po.UserQueryVo;

public class UserMapperTest {

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

	@Test
	public void testFindUserById() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		User user = userMapper.findUserById(1);

		System.out.println(user);

	}

	@Test
	public void testFindUserByUsername() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		List<User> list = userMapper.findUserByName("小明");

		System.out.println(list);

	}

	@Test
	public void testInsertUser() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		// 插入对象
		User user = new User();
		user.setUsername("李奎");
		userMapper.insertUser(user);
		sqlSession.commit();
		sqlSession.close();

	}

	// 通过包装类型查询用户信息
	@Test
	public void testFindUserList() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 构造查询条件
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		userCustom.setUsername("小明");
		userCustom.setSex("1");
		userQueryVo.setUserCustom(userCustom);
		
		//id集合
		List<Integer> ids  = new ArrayList<Integer>();
		ids.add(16);
		ids.add(22);
		userQueryVo.setIds(ids);

		List<User> list = userMapper.findUserList(userQueryVo);

		sqlSession.close();

		System.out.println(list);

	}

	// 使用resultMap进行结果映射 
	@Test
	public void testFindUserListResultMap() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 构造查询条件
		UserQueryVo userQueryVo = new UserQueryVo();
		
		UserCustom userCustom = new UserCustom();
		userCustom.setUsername("小明");
		userQueryVo.setUserCustom(userCustom);

		List<User> list = userMapper.findUserListResultMap(userQueryVo);

		sqlSession.close();

		System.out.println(list);

	}

	// 返回查询记录总数
	@Test
	public void testFindUserCount() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 构造查询条件
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		userCustom.setUsername("小明");
		userQueryVo.setUserCustom(userCustom);

		int count = userMapper.findUserCount(userQueryVo);

		sqlSession.close();

		System.out.println(count);

	}

}
