package test.dao;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.CollectionNoteDao;
import cn.tedu.cloud_note.dao.NoteBookDao;
import cn.tedu.cloud_note.entity.CollectionNote;
import cn.tedu.cloud_note.entity.ShareNote;

public class TestCollectionNoteDao {
	
	private CollectionNoteDao dao;
	@Before
	public void init() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-applicationContext.xml");
		dao=ac.getBean("collectionNoteDao", CollectionNoteDao.class);
	}
	@Test
	public void testaddNote() {
		CollectionNote cn=new CollectionNote();
		cn.setCn_collection_id("111");
		cn.setCn_collection_user_id("a4fba13e980e42008da2e499b9f3f7d5");
		cn.setCn_collection_share_id("");
		int rows=dao.addNote(cn);
		System.out.println(rows);
	}
	
	@Test
	public void testFindByUserId() {
		List<Map<String, String>> list=dao.findByUserId("a4fba13e980e42008da2e499b9f3f7d5");
		System.out.println(list);
		
	}
	
	@Test
	public void testFindByCollectionId() {
		ShareNote sn=dao.findByCollectionId("44da3a8398cd4d9f992c96218511d6c4");
		System.out.println(sn);
	}
}
