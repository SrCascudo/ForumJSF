package br.cascuda.forum.model;

import java.time.LocalTime;
import java.util.Date;

public class Publicacao {
	private Integer id;
	private String nickQuemPublicou;
	private String descricao;
	private Date dataPublicado;
	private LocalTime horaPublicado;
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

	public Date getDataPublicado() {
		return dataPublicado;
	}

	public void setDataPublicado(Date dataPublicado) {
		this.dataPublicado = dataPublicado;
	}

	public LocalTime getHoraPublicado() {
		return horaPublicado;
	}

	public void setHoraPublicado(LocalTime horaPublicado) {
		this.horaPublicado = horaPublicado;
	}

}
