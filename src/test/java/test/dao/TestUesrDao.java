package test.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;

public class TestUesrDao {
	@Test
	public void testUserDao() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-applicationContext.xml");
		UserDao dao=ac.getBean("userDao", UserDao.class);
		User user=dao.findByName("demo");
		System.out.println(user);
	}
	
	@Test
	public void testUserSave() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-applicationContext.xml");
		UserDao dao=ac.getBean("userDao", UserDao.class);
		User user=new User();
		user.setCn_user_id("123");
		user.setCn_user_name("bbb");
		user.setCn_user_nick("xixi");
		user.setCn_user_password("123");
		dao.save(user);
	}
}
