package cn.demo.mybatis.mapper;

import java.util.List;

import cn.demo.mybatis.po.User;
import cn.demo.mybatis.po.UserQueryVo;

/**
 * 接口
 * @author keehang
 *
 */
public interface UserMapper {
	
	//根据用户id查询用户信息
	public User findUserById(int id) throws Exception;
	
	//根据用户名称  查询用户信息
	public List<User> findUserByName(String username) throws Exception;
	
	//自定义查询条件查询用户信息
	public List<User> findUserList(UserQueryVo userQueryVo) throws Exception;
	
	//查询用户，使用resultMap进行映射
	public List<User> findUserListResultMap(UserQueryVo userQueryVo)throws Exception;
	//查询用户，返回记录个数
	public int findUserCount(UserQueryVo userQueryVo) throws Exception;
	
	//插入用户
	public void insertUser(User user)throws Exception;
	//删除用户
	public void deleteUser(int id) throws Exception;
	//修改用户
	public void updateUser(User user) throws Exception;
	
	

}
