package br.cascuda.forum.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.cascuda.forum.dao.UserServerDao;
import br.cascuda.forum.model.UserServer;
import br.cascuda.forum.util.Util;

@Named
@RequestScoped
public class SignUpController{
	private UserServer user = new UserServer();

	public void cadastrar() {
	
		//------VERIFICAÇÃO DE RECEBIMENTOS DE DADOS ATRAVÉS DO CONSOLE-------  
		System.out.println("-----------------------------------------------");
		System.out.println(getUser().getNick());
		System.out.println(getUser().getEmail().getLogIn());
		System.out.println(getUser().getPhone().getNumber());
		System.out.println(getUser().getTypeUser().getValue());
		System.out.println("-----------------------------------------------");
		//---------------------------------------------------------------------
	
		getUser().getEmail().setPassword(Util.encrypt(getUser().getEmail().getPassword()));//CRIPYTOGRAFA A SENHA 
		//ANTES DE SER ENVIADA AO BANCO
		
		UserServerDao comando = new UserServerDao();
		comando.create(getUser());
		comando.encerrarConexao();
		
		Util.redirect("login.xhtml");
	}
	
	public UserServer getUser() {
		return user;
	}

	public void setUser(UserServer user) {
		this.user = user;
	}
	
}
