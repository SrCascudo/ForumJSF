package br.cascuda.forum.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class UserServer{

	
	private Integer id;
	
	@NotEmpty(message="Insira seu nome")
	private String name;
	
	@NotEmpty(message="Insira seu apelido")
	private String nick;
	private EmailAcess email = new EmailAcess();
	private Phone phone = new Phone();
	private TypeUser typeUser = TypeUser.ROOT;
	private Boolean isConnected = false;
	private List<Publicacao> publicacoes = new ArrayList<Publicacao>(); 
	
	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}
	public void setPublicacoes(List<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public EmailAcess getEmail() {
		return email;
	}
	public void setEmail(EmailAcess email) {
		this.email = email;
	}
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	public TypeUser getTypeUser() {
		return typeUser;
	}
	public void setTypeUser(TypeUser typeUser) {
		this.typeUser = typeUser;
	}
	public Boolean getIsConnected() {
		return isConnected;
	}
	public void setIsConnected(Boolean isConnected) {
		this.isConnected = isConnected;
	}
	
	
}
