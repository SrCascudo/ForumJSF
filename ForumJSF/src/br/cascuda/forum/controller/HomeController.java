package br.cascuda.forum.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
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
	private Boolean connected = null;

	public HomeController() {
		super();
		
		connectUser = (UserServer) Session.getInstance().getAttribute("connect");
		if (connectUser == null) {
			connected = false;
		} else {
			connected = true;
		}
		
		ordenarLista();
	}

	public void ordenarLista() {
		Comparator<Publicacao> ordenarPorData = new Comparator<Publicacao>() {
			@Override
			public int compare(Publicacao o1, Publicacao o2) {
				return o2.getDataPublicado().compareTo(o1.getDataPublicado());
			}
		};
		
		Comparator<Publicacao> ordenarPorHora = new Comparator<Publicacao>() {
			@Override
			public int compare(Publicacao o1, Publicacao o2) {
				// TODO Auto-generated method stub
				return o2.getHoraPublicado().compareTo(o1.getHoraPublicado());
			}
		};
		
		Collections.sort(publicacoes, ordenarPorHora);
		Collections.sort(publicacoes, ordenarPorData);
	}
	
	public void redirectPergunta(Publicacao publicacao) {
		Session.getInstance().setAttribute("publicacao", publicacao);
		Redirect.comentarios();
	}

	public void publicar() {
		if (connected) {
			comando.publicar(getTextoPublicar(), getConnectUser().getId());
			setPublicacoes(comando.registry());
			ordenarLista();
		} else {
			Redirect.signUp();
		}
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

	public Boolean getConnected() {
		return connected;
	}

	public void setConnected(Boolean connected) {
		this.connected = connected;
	}

}
