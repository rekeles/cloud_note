package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
//将回收站中的笔记复原
public class RollbackNoteController {

	@Resource
	private NoteService noteService;
	
	@RequestMapping("/rollbacknote.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId, String bookId){
		NoteResult<Object> result=noteService.rollbackNote(noteId, bookId);
		return result;
	}
}
