package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.CollectionNoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
//É¾³ýÊÕ²ØµÄ±Ê¼Ç
public class DeleteCollectionNoteController {
	@Resource
	private CollectionNoteService collectionNoteService;
	@RequestMapping("/deletecollection.do")
	@ResponseBody
	public NoteResult<Object> execute(String collectionId){
		NoteResult<Object> result=collectionNoteService.deleteCollection(collectionId);
		return result;
	}
}
