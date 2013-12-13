package br.spring.springchat.model;

import java.io.Serializable;

public class ChatUser implements Serializable{

	private long id;
	private String name;
	private String nickname;
	
	private String actualRoom = "chat.master";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getActualRoom() {
		return actualRoom;
	}

	public void setActualRoom(String actualRoom) {
		this.actualRoom = actualRoom;
	}

	@Override
	public String toString() {
		return "ChatUser [id=" + id + ", name=" + name + ", nickname=" + nickname + ", actualRoom=" + actualRoom + "]";
	}
	
	

}
