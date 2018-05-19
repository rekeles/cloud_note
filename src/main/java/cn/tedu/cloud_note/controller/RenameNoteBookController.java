package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.NoteBookService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
//重命名笔记本
public class RenameNoteBookController {
	@Resource
	private NoteBookService noteBookService;
	
	@RequestMapping("/renamebook.do")
	@ResponseBody
	public NoteResult<Object> execute(String bookId,String bookName){
		NoteResult<Object> result=noteBookService.renameNoteBook(bookId, bookName);
		return result;
	}
}
