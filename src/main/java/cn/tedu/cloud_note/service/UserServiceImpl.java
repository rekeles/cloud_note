package cn.tedu.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;


@Service("UserService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;

	public NoteResult<User> checkLogin(String username, String password) {
		NoteResult<User> result=new NoteResult<User>();
		User user=userDao.findByName(username);
		
		if(user==null) {
			result.setStatus(1);
			result.setMsg("用户不存在");
			return result;
		}
		
		String md5password=NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5password)) {
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user);
		return result;
	}
	
	//注册用户
	public NoteResult<Object> addUser(String username,String password,String nick) {
		NoteResult<Object> result=new NoteResult<Object>();
		
		//根据用户名在数据库查找，看用户名是否已经存在
		User u=userDao.findByName(username);
		if(u!=null) {
			//1表示用户名已经存在
			result.setStatus(1);
			result.setMsg("用户名已经存在");
			return result;
		}
		
		//创建user
		User user=new User();
		user.setCn_user_name(username);
		user.setCn_user_nick(nick);
		user.setCn_user_id(NoteUtil.createId());
		user.setCn_user_password(NoteUtil.md5(password));
		userDao.save(user);
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
		
	}
	
	//用户修改密码
	public NoteResult<Object> changePasaword(String userId, String lastPassword, String newPassword) {
		NoteResult<Object> result=new NoteResult<Object>();
		User user=new User();
		user=userDao.findByUserId(userId);
		String password=NoteUtil.md5(lastPassword);
		//用户原密码与数据库中的密码不匹配
		if(!password.equals(user.getCn_user_password())) {
			result.setStatus(1);
			result.setMsg("原密码错误，请重新输入");
			return result;
		}else {
			//修改用户密码
			user.setCn_user_password(NoteUtil.md5(newPassword));
			int rows=userDao.changePassword(user);
			if(rows==1) {
				result.setStatus(0);
				result.setMsg("修改密码成功");
				return result;
			}else {
				result.setStatus(2);
				result.setMsg("修改密码异常");
				return result;
			}
		}
	}
}
