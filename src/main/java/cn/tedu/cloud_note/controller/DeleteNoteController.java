package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

//É¾³ý±Ê¼Ç
@Controller
@RequestMapping("/note")
public class DeleteNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/deletenote.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId){
		NoteResult<Object> result=noteService.removeNote(noteId);
		return result;
	}
}
