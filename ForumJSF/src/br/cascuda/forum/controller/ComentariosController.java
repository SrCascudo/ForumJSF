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
import br.cascuda.forum.util.Session;

@Named
@RequestScoped
public class ComentariosController {

	private Publicacao publicacao = new Publicacao();
	List<Publicacao> comentarios = new ArrayList<Publicacao>();
	private Publicacao comentario = new Publicacao();
	private Publicacao comentarioEdit = new Publicacao();
	private Boolean show = false;

	public ComentariosController() {
		// TODO Auto-generated constructor stub
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		publicacao = (Publicacao) flash.get("publicacao");
		flash.keep("publicacao");
		
		PublicacaoDao comando = new PublicacaoDao();
		comentarios = comando.takeComentarios(publicacao.getId());
		comentarioEdit = (Publicacao) flash.get("comentario");
		comando.encerrarConexao();
	}

	public void addComentario() {
		System.out.println("Add Comentario");
		PublicacaoDao comando = new PublicacaoDao();
		UserServer user = (UserServer) Session.getInstance().getAttribute("connect");
		
		comando.criarComentario(getComentario(), getPublicacao().getId(), user.getId());
		setComentarios(comando.takeComentarios(publicacao.getId()));
	}

	public void editarComentario(Publicacao publicacao) {
		System.out.println("Editar");
		PublicacaoDao comando = new PublicacaoDao();
		comando.atualizarComentario(publicacao);
	}

	public Boolean isEdit(Publicacao publicacao) {
		if (getComentarioEdit() != null)
			if (publicacao.getId() == getComentarioEdit().getId())
				return true;
		return false;
	}

	public Publicacao getComentario() {
		return comentario;
	}

	public void setComentario(Publicacao comentario) {
		this.comentario = comentario;
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
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
