package cn.tedu.cloud_note.service;

import java.util.List;

import cn.tedu.cloud_note.entity.NoteBook;
import cn.tedu.cloud_note.util.NoteResult;

public interface NoteBookService {
	//根据用户的userId查询到该用户的所有笔记本
	NoteResult<List<NoteBook>> loadUserBook(String userId);
	
	//添加笔记本
	NoteResult<NoteBook> addNoteBook(String name,String userId);
	
	//修改笔记本名字
	NoteResult<Object> renameNoteBook(String bookId,String bookName);
	
	//删除笔记本
	NoteResult<Object> deleteNoteBook(String bookId);
}
