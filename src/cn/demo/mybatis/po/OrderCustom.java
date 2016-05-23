package cn.demo.mybatis.po;

/**
 * 订单扩展对象，用于完成订单和用户查询结果 映射
 * @author keehang
 *
 */
public class OrderCustom extends Orders {
	
	//补充用户信息
	private String username;
	
	private String sex;
	
	private String address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "OrderCustom [username=" + username + ", sex=" + sex
				+ ", address=" + address + "]";
	}
	
	

}
