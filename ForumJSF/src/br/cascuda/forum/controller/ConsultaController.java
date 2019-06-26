package br.cascuda.forum.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import br.cascuda.forum.dao.PublicacaoDao;
import br.cascuda.forum.model.Publicacao;
import br.cascuda.forum.model.TipoPublicacao;
import br.cascuda.forum.model.UserServer;
import br.cascuda.forum.redirect.Redirect;
import br.cascuda.forum.util.Session;
import br.cascuda.forum.util.Util;

@Named
@RequestScoped
public class ConsultaController implements Serializable {

	private static final long serialVersionUID = -1905862185158065738L;
	private UserServer connectUser = new UserServer();
	private List<Publicacao> publicacoes = new ArrayList<Publicacao>();
	
	public ConsultaController() {
		PublicacaoDao comando = new PublicacaoDao();
		connectUser = (UserServer) Session.getInstance().getAttribute("connect");
		publicacoes = comando.takePublicaoUser(getConnectUser().getId());
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
	
	public void edit(Publicacao publicacao) {
		PublicacaoDao comando = new PublicacaoDao();

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		if (publicacao.getTipo().getValue() == 2) {
			Session.getInstance().setAttribute("publicacao", comando.publicacaoByComentario(publicacao.getId()));
			flash.put("comentario", publicacao);
		} else {
			Session.getInstance().setAttribute("publicacao", publicacao);
		}
		Redirect.comentarios();
	}

	public void delete(Publicacao publicacao) {
		PublicacaoDao comando = new PublicacaoDao();
		if (publicacao.getTipo() == TipoPublicacao.COMENTARIO) {
			comando.deleteComentario(publicacao.getId());
		} else {
			comando.delete(publicacao);
		}
		setPublicacoes(comando.takePublicaoUser(getConnectUser().getId()));
	}

	public void sair() {
		Session.getInstance().invalidateSession();
		Util.redirect("");
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
