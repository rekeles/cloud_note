package test.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestNoteService {
	
	@Resource
	private NoteService noteService;
	@Before
	public void init() {
		String[] conf= {"conf/spring-applicationContext.xml","conf/spring-applicationContext.xml"};
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		noteService=ac.getBean("noteService", NoteService.class);
	}
	
	@Test
	public void testLoadNote(){
		NoteResult<List<Map<String, String>>> result=noteService.loadNote("6d763ac9-dca3-42d7-a2a7-a08053095c08");
		System.out.println(result.toString());
	}
	
	@Test
	public void testLoad() {
		NoteResult<Note> result=noteService.load("01da5d69-89d5-4140-9585-b559a97f9cb0");
		System.out.println(result.toString());
	}
	
	@Test
	public void testUpdate() {
		NoteResult<Object> result=noteService.updateNote("01da5d69-89d5-4140-9585-b559a97f9cb0", "标题", "内容");
		System.out.println(result.getStatus());
	}
	
	@Test
	public void testAdd() {
		NoteResult<String> result=noteService.addNote("222", "2222", "这是一个标题");
		System.out.println(result);
	}
}
