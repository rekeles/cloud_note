package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;

public interface UserService {
	//用户登录
	 NoteResult<User> checkLogin(String username,String password);
	
	//用户注册
	 NoteResult<Object> addUser(String username,String password,String nick);
	 
	 //修改密码
	 NoteResult<Object> changePasaword(String userId,String lastPassword,String newPassword);
	
}
