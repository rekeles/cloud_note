package cn.tedu.cloud_note.dao;

import cn.tedu.cloud_note.entity.User;

public interface UserDao {
	//根据用户id查找用户
	User findByUserId(String userId);
	
	//根据用户名查找用户
	 User findByName(String name);
	 
	 //添加用户
	 void save(User user);
	 
	 //修改密码
	 int changePassword(User user);
}
