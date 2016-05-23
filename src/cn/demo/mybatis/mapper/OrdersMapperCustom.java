package cn.demo.mybatis.mapper;

import java.util.List;

import cn.demo.mybatis.po.OrderCustom;
import cn.demo.mybatis.po.Orders;
import cn.demo.mybatis.po.User;

/**
 * 
 * @author keehang
 *
 */
public interface OrdersMapperCustom {
	// 一对一查询，查询订单关联查询用户，使用resultType
	public List<OrderCustom> findOrderUserList() throws Exception;

	// 一对一查询，使用resultMap
	public List<Orders> findOrderUserListResultMap() throws Exception;
	//一对一查询，延迟加载
	public List<Orders> findOrderUserListLazyLoading() throws Exception;

	// 一对多查询，使用resultMap
	public List<Orders> findOrderAndOrderDetails() throws Exception;
	
	// 一对多查询，使用resultMap
	public List<User> findUserOrderDetail() throws Exception;
}
