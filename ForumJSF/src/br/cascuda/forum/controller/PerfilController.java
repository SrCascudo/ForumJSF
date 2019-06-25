package br.cascuda.forum.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import br.cascuda.forum.dao.UserServerDao;
import br.cascuda.forum.model.UserServer;
import br.cascuda.forum.util.Session;

@Named
@RequestScoped
public class PerfilController {
	private UserServer user = new UserServer();

	public PerfilController() {
		UserServerDao comando = new UserServerDao();
		user = (UserServer) Session.getInstance().getAttribute("connect");
		user = comando.takeUserById(user.getId());
		comando.encerrarConexao();
	}

	public void alterar() {

		// ------VERIFICAÇÃO DE RECEBIMENTOS DE DADOS ATRAVÉS DO CONSOLE-------
		System.out.println("-----------------------------------------------");
		System.out.println(getUser().getNick());
		System.out.println(getUser().getEmail().getLogIn());
		System.out.println(getUser().getPhone().getNumber());
		System.out.println(getUser().getTypeUser().getValue());
		System.out.println("-----------------------------------------------");
		// ---------------------------------------------------------------------

		UserServerDao comando = new UserServerDao();
		comando.update(getUser());
	}

	public UserServer getUser() {
		return user;
	}

	public void setUser(UserServer user) {
		this.user = user;
	}

}
