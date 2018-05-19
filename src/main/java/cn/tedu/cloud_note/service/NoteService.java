package cn.tedu.cloud_note.service;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;

public interface NoteService {
	
	//根据笔记本id查询笔记
	NoteResult<List<Map<String, String>>> loadNote(String bookId);
	
	//根据笔记id查询笔记的相关内容
	NoteResult<Note> load(String noteId);
	
	
	//更新笔记内容
	NoteResult<Object> updateNote(String noteId,String title,String body);
	
	//添加笔记
	NoteResult<String> addNote(String userId,String bookId,String noteTitle);
	
	//删除笔记(将笔记本放进回收站)
	NoteResult<Object> removeNote(String noteId);
	
	//根据用户id查询回收站的笔记 
	NoteResult<List<Map<String, String>>> searchDelete(String userId);
	
	//彻底删除笔记
	NoteResult<Object> deleteNote(String noteId);
	
	//将笔记移动到另一个笔记本中
	NoteResult<Object> moveNote(String noteId,String bookId);
	
	//将回收站中的笔记复原
	NoteResult<Object> rollbackNote(String noteId,String bookId);
}
