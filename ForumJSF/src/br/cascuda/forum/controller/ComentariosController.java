package br.cascuda.forum.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import br.cascuda.forum.dao.PublicacaoDao;
import br.cascuda.forum.model.Publicacao;
import br.cascuda.forum.util.Session;

@Named
@RequestScoped
public class ComentariosController {

	Publicacao publicacao = new Publicacao();
	List<Publicacao> comentarios = new ArrayList<Publicacao>();

	public ComentariosController() {
		// TODO Auto-generated constructor stub
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		publicacao = (Publicacao) flash.get("publicacao");
		PublicacaoDao comando = new PublicacaoDao();
		comentarios = comando.takeComentarios(publicacao.getId());
		comando.encerrarConexao();
	}
	
	
	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}

	public List<Publicacao> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Publicacao> comentarios) {
		this.comentarios = comentarios;
	}

}
