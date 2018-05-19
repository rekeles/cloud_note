package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.ShareNote;
import cn.tedu.cloud_note.service.ShareNoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
//根据关键字查询分享笔记
public class SearchShareController {
	
	@Resource
	private ShareNoteService shareNoteService;
	
	@RequestMapping("/searchshare.do")
	@ResponseBody
	public NoteResult<List<ShareNote>> execute(String keyword,int page) {
		NoteResult<List<ShareNote>> result=shareNoteService.findByKeyword(keyword, page);
		return result;
	}
}
