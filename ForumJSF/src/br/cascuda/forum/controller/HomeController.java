package br.cascuda.forum.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import br.cascuda.forum.dao.PublicacaoDao;
import br.cascuda.forum.model.Publicacao;
import br.cascuda.forum.model.UserServer;
import br.cascuda.forum.redirect.Redirect;
import br.cascuda.forum.util.Session;

@Named
@RequestScoped
public class HomeController {
	PublicacaoDao comando = new PublicacaoDao();
	private UserServer connectUser = new UserServer();
	private List<Publicacao> publicacoes = getComando().registry();
	private Publicacao textoPublicar = new Publicacao();
	Comparator<Publicacao> comparator = new Comparator<Publicacao>() {
		@Override
		public int compare(Publicacao o1, Publicacao o2) {
			return o2.getQuandoPublicado().compareTo(o1.getQuandoPublicado());
		}
	};

	public HomeController() {
		super();
		Collections.sort(publicacoes, comparator);
		connectUser = (UserServer) Session.getInstance().getAttribute("connect");
		System.out.println(connectUser.getId());
	}

	public void redirectPergunta(Publicacao publicacao) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("publicacao", publicacao);
		Redirect.comentarios();
	}

	public void publicar() {
		comando.publicar(getTextoPublicar(), getConnectUser().getId());
		setPublicacoes(comando.registry());
		Collections.sort(publicacoes, comparator);
	}

	public PublicacaoDao getComando() {
		return comando;
	}

	public void setComando(PublicacaoDao comando) {
		this.comando = comando;
	}

	public Publicacao getTextoPublicar() {
		return textoPublicar;
	}

	public void setTextoPublicar(Publicacao textoPublicar) {
		this.textoPublicar = textoPublicar;
	}

	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(List<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}

	public UserServer getConnectUser() {
		return connectUser;
	}

	public void setConnectUser(UserServer connectUser) {
		this.connectUser = connectUser;
	}

}
