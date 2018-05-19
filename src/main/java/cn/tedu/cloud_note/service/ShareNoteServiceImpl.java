package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.dao.ShareNoteDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.entity.ShareNote;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("shareNoteService")
public class ShareNoteServiceImpl implements ShareNoteService {
	
	@Resource
	private ShareNoteDao shareNoteDao;
	@Resource
	private NoteDao noteDao;
	public NoteResult<Object> share(String noteId) {
		
		NoteResult<Object> result=new NoteResult<Object>();
		ShareNote shareNote;
		//先查询cn_share表是否已有数据，避免重复传入相同的数据
		shareNote=shareNoteDao.findByNoteId(noteId);
		if(shareNote!=null) {
			result.setStatus(1);
			result.setMsg("您已经分享过该笔记");
			return result;
		}
		//cn_share表中没有相关数据
		Note note=noteDao.findByNoteId(noteId);
		
		shareNote=new ShareNote();
		shareNote.setCn_note_id(noteId);
		shareNote.setCn_share_body(note.getCn_note_body());
		shareNote.setCn_share_id(NoteUtil.createId());
		shareNote.setCn_share_title(note.getCn_note_title());
		
		int rows=shareNoteDao.shareNote(shareNote);
		if(rows==1) {
			result.setStatus(0);
			result.setMsg("分享成功");
			return result;
		}else {
			result.setStatus(2);
			result.setMsg("分享笔记异常");
			return result;
		}
	}
	public NoteResult<List<ShareNote>> findByKeyword(String keyword, int page) {
		NoteResult<List<ShareNote>> result=new NoteResult<List<ShareNote>>();
		//构建shareNoteDao的参数
		String title="%"+keyword+"%";
		int begin=(page-1)*3;
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("title", title);
		params.put("begin", begin);
		
		List<ShareNote> list=shareNoteDao.findByKeyword(params);
		result.setData(list);
		result.setMsg("查询成功");
		result.setStatus(0);
		return result;
	}
	public NoteResult<ShareNote> findByShareNoteId(String shareNoteId) {
		NoteResult<ShareNote> result=new NoteResult<ShareNote>();
		ShareNote shareNote=shareNoteDao.findByShareNoteId(shareNoteId);
		
		result.setData(shareNote);
		result.setStatus(0);
		result.setMsg("查询分享笔记成功");
		return result;
	}

}
