package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.ShareNoteService;
import cn.tedu.cloud_note.util.NoteResult;

//∑÷œÌ± º«
@Controller
@RequestMapping("/note")
public class ShareNoteController {
	@Resource
	private ShareNoteService shareNoteService;
	
	@RequestMapping("/sharenote.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId){
		NoteResult<Object> result=shareNoteService.share(noteId);
		return result;
	}
}
