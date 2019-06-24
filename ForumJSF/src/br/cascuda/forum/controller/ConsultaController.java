package br.cascuda.forum.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.cascuda.forum.dao.PublicacaoDao;
import br.cascuda.forum.model.Publicacao;
import br.cascuda.forum.model.UserServer;
import br.cascuda.forum.redirect.Redirect;
import br.cascuda.forum.util.Session;
import br.cascuda.forum.util.Util;

@Named
@RequestScoped
public class ConsultaController implements Serializable {

	private static final long serialVersionUID = -1905862185158065738L;
	private UserServer connectUser = new UserServer();
	private LocalDate buscarApartirData = null;
	private List<Publicacao> publicacoes = new ArrayList<Publicacao>();
	Comparator<Publicacao> comparator = new Comparator<Publicacao>() {
        @Override
        public int compare(Publicacao o1, Publicacao o2) {
            return o2.getQuandoPublicado().compareTo(o1.getQuandoPublicado());
        }
    };
	
	public ConsultaController() {
		PublicacaoDao comando = new PublicacaoDao();
		connectUser = (UserServer) Session.getInstance().getAttribute("connect");
		publicacoes = 	comando.takePublicaoUser(getConnectUser().getId());
		Collections.sort(getPublicacoes(), comparator);
	}

	public void edit(Publicacao publicacao) {
		PublicacaoDao comando = new PublicacaoDao();
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		if (publicacao.getTipo().getValue() == 2) {
			flash.put("publicacao", comando.publicacaoByComentario(publicacao.getId()));
			flash.put("comentario", publicacao);
		}else {
			flash.put("publicacao", publicacao);
		}
		Redirect.comentarios();
	}

	public void delete(Publicacao user) {

	}
	
	public void pesquisar() {
		PublicacaoDao comando = new PublicacaoDao();
		if (getBuscarApartirData() != null) {

		} else {
			setPublicacoes(comando.takePublicaoUser(getConnectUser().getId()));
			comando.encerrarConexao();
			Collections.sort(getPublicacoes(), comparator);
		}
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

	public LocalDate getBuscarApartirData() {
		return buscarApartirData;
	}

	public void setBuscarApartirData(LocalDate buscarApartirData) {
		this.buscarApartirData = buscarApartirData;
	}

	public UserServer getConnectUser() {
		return connectUser;
	}

	public void setConnectUser(UserServer connectUser) {
		this.connectUser = connectUser;
	}
}
