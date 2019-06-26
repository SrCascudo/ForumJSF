package br.cascuda.forum.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EmailAcess {

	@NotEmpty(message = "Insira seu Email")
	@Email(message="Insira email Válido")
	private String logIn;
	
	@NotEmpty(message = "Insira sua senha")
	@Size(message = "A senha deve ter de 6 a 15 caracteres" , max = 15 , min = 6)
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
