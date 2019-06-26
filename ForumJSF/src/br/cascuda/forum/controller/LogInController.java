package br.cascuda.forum.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.cascuda.forum.dao.LoginDao;
import br.cascuda.forum.model.EmailAcess;
import br.cascuda.forum.model.UserServer;
import br.cascuda.forum.util.Session;
import br.cascuda.forum.util.Util;

@Named
@RequestScoped
public class LogInController{
	private EmailAcess access = new EmailAcess();
	
	private Boolean validation() {
		getAccess().setPassword(Util.encrypt(getAccess().getPassword()));//CRIPYTOGRAFA SENHA INFOMADA PARA VERIFICAÇÃO
		LoginDao buscar = new LoginDao();
		for (UserServer element: buscar.registry()) {
			if(element.getEmail().getLogIn().equals(getAccess().getLogIn())) {
				System.out.println("LOGIN VÁLIDO! ;)");
				if (element.getEmail().getPassword().equals(getAccess().getPassword())) {
					System.out.println("SENHA VÁLIDA");
					Session.getInstance().setAttribute("connect", element);
					buscar.encerrarConexao();
					return true;
				}
			}
		}
		return false;
	}
	
	public void start() {
		if (validation()) {
			System.out.println("ACESSO LIBERADO");
			Util.redirect("/ForumJSF/faces/home.xhtml");
			
		} else {
			System.out.println("ACESSO NEGADO");
		}
	}
	
	public EmailAcess getAccess() {
		return access;
	}

	public void setAccess(EmailAcess access) {
		this.access = access;
	}
}
