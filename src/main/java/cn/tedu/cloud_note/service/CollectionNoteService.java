package cn.tedu.cloud_note.service;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.ShareNote;
import cn.tedu.cloud_note.util.NoteResult;

public interface CollectionNoteService {
	//收藏笔记
	NoteResult<Object> collectNote(String userId,String shareId);
	
	//查看收藏的笔记列表
	NoteResult<List<Map<String, String>>> searchCollection(String userId);
	
	//查看收藏笔记的内容
	NoteResult<ShareNote> loadCollectionNote(String collectionId);
	
	//删除收藏的笔记
	NoteResult<Object> deleteCollection(String collectionId);
}
