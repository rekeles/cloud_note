package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.ShareNote;
import cn.tedu.cloud_note.service.ShareNoteService;
import cn.tedu.cloud_note.util.NoteResult;

//根据分享笔记id查询分享笔记的内容
@Controller
@RequestMapping("/note")
public class SearchShareNoteController {
	
	@Resource
	private ShareNoteService shareNoteService;
	
	@RequestMapping("/loadshare.do")
	@ResponseBody
	public NoteResult<ShareNote> execute(String shareId){
		NoteResult<ShareNote> result=shareNoteService.findByShareNoteId(shareId);
		return result;
	}
	
}
