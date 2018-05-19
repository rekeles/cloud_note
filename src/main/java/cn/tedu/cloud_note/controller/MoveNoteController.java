package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class MoveNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/move.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId, String bookId){
		NoteResult<Object> result=noteService.moveNote(noteId, bookId);
		return result;
	}
}
