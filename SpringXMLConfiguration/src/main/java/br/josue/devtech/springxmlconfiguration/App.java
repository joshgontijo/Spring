package br.josue.devtech.springxmlconfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 * 
 */
public class App {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring.xml");

		Properties props = context.getBean(Properties.class);
		System.out.println(props.getName());
		System.out.println(props.getAge());
	}
}
