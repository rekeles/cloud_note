package test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.NoteBookDao;
import cn.tedu.cloud_note.entity.NoteBook;

public class TestNoteBookDao {
	
	private NoteBookDao dao;
	@Before
	public void init() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-applicationContext.xml");
		dao=ac.getBean("noteBookDao", NoteBookDao.class);
	}
	@Test
	public void testFindByUserId() {
		List<NoteBook> list=dao.findByUserId("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
		System.out.println(list);
	}
	
	@Test
	public void testAdd(){
		NoteBook noteBook=new NoteBook();
		noteBook.setCn_notebook_id("12");
		noteBook.setCn_notebook_name("笔记本名啊");
		noteBook.setCn_user_id("22");
		dao.add(noteBook);
		
	}
	
	@Test
	public void testUpdate() {
		NoteBook noteBook=new NoteBook();
		noteBook.setCn_notebook_id("2dc8d47153a44012940a794bae1e5e93");
		noteBook.setCn_notebook_name("笔记本名啊");
		int rows=dao.update(noteBook);
		System.out.println(rows);
	}
}