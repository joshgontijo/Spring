package br.spring.springchat.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ChatMessage implements Serializable{

	private ChatUser from;
	private List<ChatUser> recipients;
	private String body;
	private Date sentTime;

	public ChatUser getFrom() {
		return from;
	}

	public void setFrom(ChatUser from) {
		this.from = from;
	}

	public List<ChatUser> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<ChatUser> recipients) {
		this.recipients = recipients;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	@Override
	public String toString() {
		return "ChatMessage [from=" + from + ", recipients=" + recipients + ", body=" + body + ", sentTime=" + sentTime
				+ "]";
	}

}
