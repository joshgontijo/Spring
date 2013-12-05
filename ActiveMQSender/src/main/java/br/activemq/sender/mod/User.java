package br.activemq.sender.mod;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public User(String name, int idade) {
		super();
		this.name = name;
		this.idade = idade;
	}

	private String name;
	private int idade;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", idade=" + idade + "]";
	}

}
