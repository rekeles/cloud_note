package cn.tedu.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class LoadNoteController {
	
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/loadnotes.do")
	@ResponseBody//jsonÊä³ö
	public NoteResult<List<Map<String, String>>> execute(String bookId){
		NoteResult<List<Map<String, String>>> result=noteService.loadNote(bookId);
		return result;
	}
}
