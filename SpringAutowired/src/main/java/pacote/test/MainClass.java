package pacote.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	@Autowired(required = false)
	private Person person;

	public MainClass() {

	}

	public static void main(String... args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring.xml" });

		Person cust = (Person) context.getBean("person");
		cust.walk();
                
                //Or
                
//              ApplicationContext context = new AnnotationConfigApplicationContext("pacote.test");
//		MainClass cust = (MainClass) context.getBean(MainClass.class);
//		cust.person.walk();
//              //and add @Component to classes (remove xml spring config)
                
	}
}
