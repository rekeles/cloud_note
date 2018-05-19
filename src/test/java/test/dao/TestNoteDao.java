package test.dao;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;

public class TestNoteDao {
	
	private NoteDao dao;
	@Before
	public void init() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-applicationContext.xml");
		dao=ac.getBean("noteDao", NoteDao.class);
	}
	
	@Test
	public void testFindByBookId() {
		
		List<Map<String, String>> list=dao.findByBookId("6d763ac9-dca3-42d7-a2a7-a08053095c08");
		for(Map map:list) {
			System.out.println(map.get("cn_note_id")+","+map.get("cn_note_title"));
		}
		
	}
	
	@Test
	public void testFindByNoteId() {
		Note note=dao.findByNoteId("01da5d69-89d5-4140-9585-b559a97f9cb0");
		System.out.println(note.toString());
	}
	
	@Test
	public void testUpdate() {
		Note note=dao.findByNoteId("01da5d69-89d5-4140-9585-b559a97f9cb0");
		note.setCn_note_body("新的内容");
		note.setCn_note_title("新的标题");
		int status=dao.updateNote(note);
	}
	
	@Test
	public void testAdd() {
		Note note=new Note();
		note.setCn_note_body("笔记内容");
		long time=System.currentTimeMillis();
		note.setCn_note_create_time(time);
		note.setCn_note_id("111");
		note.setCn_note_last_modify_time(time);
		note.setCn_note_status_id("1");
		note.setCn_note_title("新笔记");
		note.setCn_note_type_id("1");
		note.setCn_notebook_id("1111");
		note.setCn_user_id("11");
		int rows=dao.addNote(note);
		System.out.println(rows);
	}
	
	@Test
	public void testDeleteByNoteId() {
		int rows=dao.deleteByNoteId("a21c1a819f0941daaff642d36cee4c8b");
		System.out.println(rows);
	}
}
