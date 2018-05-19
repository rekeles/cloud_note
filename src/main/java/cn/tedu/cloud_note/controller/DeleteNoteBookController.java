package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.NoteBookService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
//É¾³ý±Ê¼Ç±¾
public class DeleteNoteBookController {
	@Resource
	private NoteBookService noteBookService;
	@RequestMapping("/deletebook.do")
	@ResponseBody
	public NoteResult<Object> execute(String bookId){
		NoteResult<Object> result=noteBookService.deleteNoteBook(bookId);
		return result;
	}
}
