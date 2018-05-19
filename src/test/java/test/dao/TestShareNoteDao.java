package test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.ShareNoteDao;
import cn.tedu.cloud_note.entity.ShareNote;

public class TestShareNoteDao {
	
	private ShareNoteDao dao;
	@Before
	public void init() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-applicationContext.xml");
		dao=ac.getBean("shareNoteDao", ShareNoteDao.class);
	}
	
	@Test
	public void testShareNote() {
		ShareNote sn=new ShareNote();
		sn.setCn_note_id("1");
		sn.setCn_share_body("笔记内容");
		sn.setCn_share_id("11");
		sn.setCn_share_title("笔记标题");
		int rows=dao.shareNote(sn);
		System.out.println(rows);
		
	}
	
	@Test
	public void testFindByNoteId() {
		ShareNote sn=dao.findByNoteId("7511b06847c14bc88e8b00b6355ef81b");
		System.out.println(sn);
	}
	
	@Test
	public void testFindByKeyword() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("title", "%笔记%");
		map.put("begin", 0);
		List<ShareNote> list=dao.findByKeyword(map);
		System.out.println(list);
	}
	
	@Test
	public void testFindByShareNoteId() {
		ShareNote sn=dao.findByShareNoteId("7f0fc8393c414e7e9a733f7e56fdf4f3");
		System.out.println(sn.toString());
	}
}
