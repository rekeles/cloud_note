package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

//Ìí¼Ó±Ê¼Ç
@Controller
@RequestMapping("/note")
public class AddNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/addnote.do")
	@ResponseBody
	public NoteResult<String> execute(String userId,String bookId,String noteTitle){
		NoteResult<String> result=noteService.addNote(userId, bookId, noteTitle);
		return result;
	}
}
