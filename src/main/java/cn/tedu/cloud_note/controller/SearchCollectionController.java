package cn.tedu.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.CollectionNoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
//查看收藏笔记
public class SearchCollectionController {
	
	@Resource
	private CollectionNoteService collectionNoteService;
	
	@RequestMapping("/searchcollection.do")
	@ResponseBody
	public NoteResult<List<Map<String, String>>> execute(String userId){
		NoteResult<List<Map<String, String>>> result=collectionNoteService.searchCollection(userId);
		return result;
	}
}
