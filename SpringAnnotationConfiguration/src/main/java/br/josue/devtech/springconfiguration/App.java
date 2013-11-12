package br.josue.devtech.springconfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 * 
 */
public class App {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Properties.class);
		context.refresh();
		Properties props = context.getBean(Properties.class);
		System.out.println(props.getName());
		System.out.println(props.getAge());
	}
}
