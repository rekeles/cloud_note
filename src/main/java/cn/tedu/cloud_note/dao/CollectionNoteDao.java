package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.CollectionNote;
import cn.tedu.cloud_note.entity.ShareNote;

public interface CollectionNoteDao {
	//收藏笔记(添加笔记)
	int addNote(CollectionNote collectionNote);
	
	//根据分享笔记id查找收藏表中的记录
	CollectionNote findByShareId(String shareId);
	
	//根据用户id查找用户的收藏笔记
	List<Map<String, String>> findByUserId(String userId);
	
	//查看收藏笔记的内容
	ShareNote findByCollectionId(String collectionId);
	
	//根据collectionid删除收藏的笔记
	int deleteByCollectionId(String collectionId);
}
