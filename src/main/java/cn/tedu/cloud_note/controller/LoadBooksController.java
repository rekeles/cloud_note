package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.NoteBook;
import cn.tedu.cloud_note.service.NoteBookService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")//匹配请求路径
//根据用户ID查询用户的所有笔记本，返回json给前端
public class LoadBooksController {
	@Resource
	private NoteBookService noteBookService;
	
	@RequestMapping("/loadbooks.do")//匹配请求
	@ResponseBody//json输出
	public NoteResult<List<NoteBook>> execute(String userId){
		
		NoteResult<List<NoteBook>> result=noteBookService.loadUserBook(userId);
		return result;
	}
}
