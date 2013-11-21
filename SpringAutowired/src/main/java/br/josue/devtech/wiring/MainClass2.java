package br.josue.devtech.wiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainClass2 {

	@Autowired
	private Person2 person2;

	@Autowired
	AnotherClass ac;

	// Initial point for bean creation on standalone applications
	// when using spring this way, first initialize beans, then start the app
	public static void main(String... args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring.xml" });

		MainClass2 mainClass2 = context.getBean(MainClass2.class);
		mainClass2.person2.walk();

		mainClass2.ac.callPerson2Method();
		new AnotherClass().callPerson2Method();
	}
}
