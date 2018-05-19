package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.NoteBook;
import cn.tedu.cloud_note.service.NoteBookService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class AddNoteBookController {
	
	@Resource
	private NoteBookService noteBookService;
	
	@RequestMapping("/addbook.do")
	@ResponseBody
	public NoteResult<NoteBook> execute(String name,String userId){
		NoteResult<NoteBook> result=noteBookService.addNoteBook(name, userId);
		return result;
	}
}
