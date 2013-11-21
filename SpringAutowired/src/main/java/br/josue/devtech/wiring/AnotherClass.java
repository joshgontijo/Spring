package br.josue.devtech.wiring;

import org.springframework.beans.factory.annotation.Autowired;

//@Service
public class AnotherClass {

	@Autowired
	private Person2 person2;

	public void callPerson2Method() {
		person2.walk();
	}
}
