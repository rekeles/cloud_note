package test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.entity.ShareNote;
import cn.tedu.cloud_note.service.ShareNoteService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestShareNoteService {
	@Resource
	private ShareNoteService service;
	@Before
	public void init() {
		String[] conf= {"conf/spring-applicationContext.xml","conf/spring-applicationContext.xml"};
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		service=ac.getBean("shareNoteService", ShareNoteService.class);
	}
	
	@Test
	public void testShare() {
		NoteResult<Object> result=service.share("3d48932a144a4d2198b19256cca0eac9");
		System.out.println(result);
	}
	
	@Test
	public void testFindByKeyword() {
		NoteResult<List<ShareNote>> result=service.findByKeyword("±Ê¼Ç", 1);
		System.out.println(result);
		
	}
	
	@Test
	public void testFindByShareNoteId() {
		NoteResult<ShareNote> result=service.findByShareNoteId("7f0fc8393c414e7e9a733f7e56fdf4f3");
		System.out.println(result);
	}
}
