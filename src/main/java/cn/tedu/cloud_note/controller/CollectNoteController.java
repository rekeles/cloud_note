package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.CollectionNoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class CollectNoteController {
	@Resource
	private CollectionNoteService collectionNoteService;
	@RequestMapping("/collectnote.do")
	@ResponseBody
	public NoteResult<Object> execute(String userId, String shareId){
		NoteResult<Object> result=collectionNoteService.collectNote(userId, shareId);
		return result;
		
	}
}
