package test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestUserService {
	
	UserService userService;
	@Before
	public void init() {
		String[] conf= {"conf/spring-applicationContext.xml","conf/spring-applicationContext.xml"};
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		userService=ac.getBean("UserService", UserService.class);
	}
	
	@Test
	public void test1() {
		NoteResult<User> result=userService.checkLogin("demo", "123");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	@Test
	public void test2() {
		NoteResult<User> result=userService.checkLogin("aaa", "123");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	
	@Test
	public void test3() {
		NoteResult<User> result=userService.checkLogin("hehe", "123");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
}
