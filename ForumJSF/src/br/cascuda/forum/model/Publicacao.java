package br.cascuda.forum.model;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Publicacao {
	private Integer id;
	private String nickQuemPublicou;
	private String descricao;
	private Date dataPublicado;
	private Time horaPublicado;
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

	public Time getHoraPublicado() {
		return horaPublicado;
	}

	public void setHoraPublicado(Time horaPublicado) {
		this.horaPublicado = horaPublicado;
	}

}
