package br.josue.devtech;

import java.util.Date;
import java.util.UUID;

public class Message {

	private int id = 1;
	private String uuid = UUID.randomUUID().toString();
	private String textMessage = "Spring REST";
	private Date date = new Date();

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", uuid=" + uuid + ", textMessage=" + textMessage + ", date=" + date + "]";
	}
	
	

}
