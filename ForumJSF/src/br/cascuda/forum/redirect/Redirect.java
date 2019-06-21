package br.cascuda.forum.redirect;

import javax.inject.Named;

import br.cascuda.forum.util.Util;

@Named
public class Redirect {
	static public void consulta() {
		Util.redirect("consulta.xhtml");
	}
	
	static public void home() {
		Util.redirect("home.xhtml");
	}
	
	static public void quemSomos() {
		Util.redirect("quemSomos.xhtml");
	}
	
	static public void comentarios() {
		Util.redirect("comentarios.xhtml");
	}
}
