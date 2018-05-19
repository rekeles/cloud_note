package cn.tedu.cloud_note.dao;

import java.util.List;

import cn.tedu.cloud_note.entity.NoteBook;

public interface NoteBookDao {
	
	//根据用户id查找用户的所有笔记本
	List<NoteBook> findByUserId(String userId);
	
	//添加笔记本
	int add(NoteBook noteBook);
	
	//修改笔记本名字
	int update(NoteBook noteBook);
	
	//根据笔记本id删除笔记本
	int delete(String bookId);
}
