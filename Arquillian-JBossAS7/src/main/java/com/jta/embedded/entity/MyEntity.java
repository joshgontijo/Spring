package com.jta.embedded.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyEntity {

	@Id
	private String uuid;
	private String name;
	private String password;

	public MyEntity(){
		
	}
	
	public MyEntity(String uuid, String name, String password) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.password = password;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
