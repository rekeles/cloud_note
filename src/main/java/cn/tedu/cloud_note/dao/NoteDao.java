package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Note;

public interface NoteDao {
	//根据笔记本id查询相关的笔记
	List<Map<String,String>> findByBookId(String bookId);
	
	//根据笔记id查询笔记的相关内容
	Note findByNoteId(String noteId);
	
	//更新笔记内容,返回类型为int为update改变的行数
	int updateNote(Note note);
	
	//创建笔记
	int addNote(Note note);
	
	//删除笔记(将笔记放进回收站)
	int deleteByNoteId(String noteId);
	
	//根据用户id查询回收站的笔记
	List<Map<String,String>> findByUserId(String userId);
	
	//彻底删除笔记
	int deleteForever(String noteId);
	
	//将笔记移动到另一个笔记本中
	int moveNote(Note note);
	
	//将回收站中的笔记复原
	int rollbackNote(Note note);
	
}
