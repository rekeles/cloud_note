package cn.tedu.cloud_note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.CollectionNoteDao;
import cn.tedu.cloud_note.dao.ShareNoteDao;
import cn.tedu.cloud_note.entity.CollectionNote;
import cn.tedu.cloud_note.entity.ShareNote;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("collectionNoteService")
public class CollectionNoteServiceImpl implements CollectionNoteService {
	@Resource
	private CollectionNoteDao collectionNoteDao;
	@Resource
	private ShareNoteDao shareNoteDao;
	public NoteResult<Object> collectNote(String userId, String shareId) {
		NoteResult<Object> result=new NoteResult<Object>();
		CollectionNote collectionNote;
		//先查看收藏表中是否已有记录
		collectionNote=collectionNoteDao.findByShareId(shareId);
		if(collectionNote!=null) {
			result.setMsg("该笔记已经在收藏中");
			result.setStatus(2);
			return result;
		}else {//没有记录时，添加笔记到收藏表中
			collectionNote=new CollectionNote();
			collectionNote.setCn_collection_id(NoteUtil.createId());
			collectionNote.setCn_collection_user_id(userId);
			collectionNote.setCn_collection_share_id(shareId);
			int rows=collectionNoteDao.addNote(collectionNote);
			if(rows==1) {
				result.setMsg("收藏成功");
				result.setStatus(0);
				return result;
			}else {
				result.setMsg("收藏笔记异常");
				result.setStatus(1);
				return result;
			}
		}
		
	}
	public NoteResult<List<Map<String, String>>> searchCollection(String userId) {
		
		NoteResult<List<Map<String, String>>> result=new NoteResult<List<Map<String,String>>>();
		List<Map<String, String>> list=collectionNoteDao.findByUserId(userId);
		result.setData(list);
		result.setMsg("查看收藏成功");
		result.setStatus(0);
		return result;
	}
	public NoteResult<ShareNote> loadCollectionNote(String collectionId) {
		NoteResult<ShareNote> result=new NoteResult<ShareNote>();
		ShareNote shareNote=collectionNoteDao.findByCollectionId(collectionId);
		result.setData(shareNote);
		result.setMsg("查看笔记成功");
		result.setStatus(0);
		return result;
	}
	public NoteResult<Object> deleteCollection(String collectionId) {
		NoteResult<Object> result=new NoteResult<Object>();
		int rows=collectionNoteDao.deleteByCollectionId(collectionId);
		if(rows==1) {
			result.setMsg("删除成功");
			result.setStatus(0);
			return result;
		}else {
			result.setMsg("删除笔记异常");
			result.setStatus(1);
			return result;
		}
	}

}
