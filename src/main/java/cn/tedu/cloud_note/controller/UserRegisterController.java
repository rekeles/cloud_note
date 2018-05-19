package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user")//匹配请求路径
//用户注册
public class UserRegisterController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/add.do")//匹配请求
	@ResponseBody //json输出
	public NoteResult<Object> execute(String username,String nick,String password){
		System.out.println(username);
		
		NoteResult<Object> result=userService.addUser(username, password, nick);
		return result;
		
	}
}
