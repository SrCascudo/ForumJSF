package br.cascuda.forum.model;

import javax.validation.constraints.NotEmpty;

public class Email {

	@javax.validation.constraints.Email(message = "Insira seu Email")
	private String logIn;
	
	@NotEmpty(message = "Insira sua senha")
	private String password;
	public String getLogIn() {
		return logIn;
	}
	
	public void setLogIn(String logIn) {
		this.logIn = logIn;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
