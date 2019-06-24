package br.cascuda.forum.model;

import java.time.LocalDate;

public class Publicacao {
	private Integer id;
	private String nickQuemPublicou;
	private String descricao;
	private LocalDate quandoPublicado;
	private TipoPublicacao tipo;

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickQuemPublicou() {
		
		return nickQuemPublicou;
	}

	public void setNickQuemPublicou(String nickQuemPublicou) {
		this.nickQuemPublicou = nickQuemPublicou;
	}

	public TipoPublicacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoPublicacao tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getQuandoPublicado() {
		return quandoPublicado;
	}

	public void setQuandoPublicado(LocalDate quandoPublicado) {
		this.quandoPublicado = quandoPublicado;
	}

}
