package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user")
//”√ªß–ﬁ∏ƒ√‹¬Î
public class ChangePasswordController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/changepassword.do")
	@ResponseBody
	public NoteResult<Object> execute(String userId,String lastPassword,String newPassword){
		NoteResult<Object> result=userService.changePasaword(userId, lastPassword, newPassword);
		return result;
	}
}
