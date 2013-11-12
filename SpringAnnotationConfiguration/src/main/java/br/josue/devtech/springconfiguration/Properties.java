/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.josue.devtech.springconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * 
 * @author Josue
 */
@Configuration()
@PropertySource("classpath:user.properties")
public class Properties {

	@Value("${name}")
	private String name;

	@Value("${age}")
	private int age;


	// In order for @Value annotations to work
	// PropertySourcesPlaceholderConfigurer should be registered.
	// It is done automatically when using <context:property-placeholder> in
	// XML,
	// but should be registered as a static @Bean when using @Configuration.
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
