package org.JerseyWS;

import java.util.Date;

public class Message {

	private String name = "josue";
	private String message = "This project can run on Embedded Tomcat... check POM";
	private Date date = new Date();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
