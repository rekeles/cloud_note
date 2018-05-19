package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.ShareNote;

public interface ShareNoteDao {
	
	//分享笔记
	int shareNote(ShareNote shareNote);
	
	//根据笔记id(cn_note_id)查询分享笔记表中的记录
	ShareNote findByNoteId(String noteId);
	
	//根据笔记名关键字查询分享笔记表中的记录
	List<ShareNote> findByKeyword(Map<String, Object> map);
	
	//根据分享笔记id查询分享笔记的内容
	ShareNote findByShareNoteId(String shareNoteId);
}
