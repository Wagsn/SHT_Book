package book.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import book.dao.BookDBFactory;
import book.entity.User;



public class Test01 {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("bean.xml");
		BookDBFactory bookDBFactory=(BookDBFactory) context.getBean("bookDBFactory");
		List<User> list=bookDBFactory.getUserDao().findAll();
		for (User user : list) {
			System.out.println(user);
		}
		
		
	}
}
