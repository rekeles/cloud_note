package cn.tedu.cloud_note.service;

import java.util.List;

import cn.tedu.cloud_note.entity.ShareNote;
import cn.tedu.cloud_note.util.NoteResult;

public interface ShareNoteService {
	//分享笔记
	NoteResult<Object> share(String noteId);
	
	//根据笔记名关键字查询分享笔记表中的记录
	NoteResult<List<ShareNote>> findByKeyword(String keyword,int page);
	
	//根据分享笔记id查询分享笔记的内容
	NoteResult<ShareNote> findByShareNoteId(String shareNoteId);
}
