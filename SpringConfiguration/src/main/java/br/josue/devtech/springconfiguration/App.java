package br.josue.devtech.springconfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {


    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Properties props = (Properties) context.getBean("props");
        System.out.println(props.getStringValue());
        System.out.println(props.getIntValue());
    }
}
