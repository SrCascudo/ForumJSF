package br.cascuda.forum.redirect;

import javax.inject.Named;

import br.cascuda.forum.util.Session;
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
	
	static public void perfil() {
		Util.redirect("perfilUser.xhtml");
	}
	
	static public void sair() {
		Util.redirect("/ForumJSF/faces/login.xhtml");
		Session.getInstance().invalidateSession();
	}
}
