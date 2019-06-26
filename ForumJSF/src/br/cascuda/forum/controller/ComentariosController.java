package br.cascuda.forum.controller;

import java.util.ArrayList;
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
public class ComentariosController {

	private Publicacao publicacao = new Publicacao();
	List<Publicacao> comentarios = new ArrayList<Publicacao>();
	private Publicacao comentario = new Publicacao();
	private Publicacao comentarioEdit = new Publicacao();
	private String forEdit;

	public ComentariosController() {
		// TODO Auto-generated constructor stub
		PublicacaoDao comando = new PublicacaoDao();
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		if ((Publicacao) Session.getInstance().getAttribute("publicacao") != null) {
			publicacao = (Publicacao) Session.getInstance().getAttribute("publicacao");
			comentarios = comando.takeComentarios(publicacao.getId());
			comando.encerrarConexao();
		}
		flash.keep("comentario");
		comentarioEdit = (Publicacao) flash.get("comentario");
	}

	public void addComentario() {
		PublicacaoDao comando = new PublicacaoDao();
		UserServer user = (UserServer) Session.getInstance().getAttribute("connect");
		if (user != null) {
			comando.criarComentario(getComentario(), getPublicacao().getId(), user.getId());
			setComentarios(comando.takeComentarios(publicacao.getId()));
		} else {
			Redirect.signUp();
		}
	}

	public void editarComentario(Publicacao publicacao) {
		System.out.println(publicacao.getDescricao());
		PublicacaoDao comando = new PublicacaoDao();
		comando.atualizarComentario(publicacao);
	}
	
	public boolean isEdit(Publicacao publicacao) {
		if (getComentarioEdit() != null) {
			if (publicacao.getId() == getComentarioEdit().getId()) {
				return true;
			}
		} else {
			return false;
		}
		return null != null;
	}

	public String getForEdit() {
		return forEdit;
	}

	public void setForEdit(String forEdit) {
		this.forEdit = forEdit;
	}

	public Publicacao getComentario() {
		return comentario;
	}

	public void setComentario(Publicacao comentario) {
		this.comentario = comentario;
	}

	public Publicacao getComentarioEdit() {
		return comentarioEdit;
	}

	public void setComentarioEdit(Publicacao comentarioEdit) {
		this.comentarioEdit = comentarioEdit;
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
