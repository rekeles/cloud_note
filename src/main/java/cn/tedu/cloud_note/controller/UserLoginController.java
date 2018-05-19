package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user") //匹配请求路径
//用户登录
public class UserLoginController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do") //匹配请求
	@ResponseBody  //json输出
	public NoteResult<User> execute(String username,String password){
		NoteResult<User> result=userService.checkLogin(username, password);
		return result;
	}
}
