package br.josue.devtech.basiccreation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public MainClass() {

	}

	public static void main(String... args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring.xml" });

		Person cust = (Person) context.getBean("person");
		cust.walk();

	}
}
