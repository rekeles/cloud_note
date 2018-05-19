package test.service;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.entity.NoteBook;
import cn.tedu.cloud_note.service.NoteBookService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestNoteBookService {
	
	NoteBookService noteBookService;
	@Before
	public void init() {
		String[] conf= {"conf/spring-applicationContext.xml","conf/spring-applicationContext.xml"};
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		noteBookService=ac.getBean("noteBookService", NoteBookService.class);
		
	}
	
	@Test
	public void loadUserBookTest() {
		NoteResult<List<NoteBook>> result=noteBookService.loadUserBook("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
		System.out.println(result.toString());
	}
	
	@Test
	public void testAddNoteBook() {
		NoteResult<NoteBook> result=noteBookService.addNoteBook("笔记本笔记本", "1111");
		System.out.println(result);
	}
	
	@Test
	public void testRenameNoteBook() {
		NoteResult<Object> result=noteBookService.renameNoteBook("2dc8d47153a44012940a794bae1e5e93", "名字");
		System.out.println(result);
	}
}
