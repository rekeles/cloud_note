package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.ShareNote;
import cn.tedu.cloud_note.service.CollectionNoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
//查看收藏笔记的内容
public class LoadCollectionNoteController {
	@Resource
	private CollectionNoteService collectionNoteService;
	@RequestMapping("/loadcollection.do")
	@ResponseBody
	public NoteResult<ShareNote> execute(String collectionId){
		NoteResult<ShareNote> result=collectionNoteService.loadCollectionNote(collectionId);
		return result;
	}
}
