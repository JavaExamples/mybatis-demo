package cn.demo.mybatis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import cn.demo.mybatis.po.User;
/**
 * dao的实现类中存在重复代码，整个mybatis操作的过程代码模板重复（先创建sqlsession、调用sqlsession的方法、关闭sqlsession）
   dao的实现 类中存在硬编码，调用sqlsession方法时将statement的id硬编码。
 * @author keehang
 *
 */
public class UserDaoImpl implements UserDao {

	private SqlSessionFactory sqlSessionFactory;

	// 将SqlSessionFactory注入
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public User findUserById(int id) throws Exception {

		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 根据id查询用户信息
		User user = sqlSession.selectOne("test.findUserById", id);

		sqlSession.close();

		return user;

	}

	@Override
	public List<User> findUserByUsername(String username) throws Exception {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<User> list = sqlSession.selectList("test.findUserByName", username);
		sqlSession.close();
		return list;
	}

	@Override
	public void insertUser(User user) throws Exception {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("test.insertUser", user);
		sqlSession.commit();
		sqlSession.close();
		
	}

}
