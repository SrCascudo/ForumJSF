package br.cascuda.forum.redirect;

import javax.inject.Named;

import br.cascuda.forum.util.Session;
import br.cascuda.forum.util.Util;

@Named
public class Redirect {
	static public void consulta() {
		Util.redirect("/ForumJSF/faces/pages/consulta.xhtml");
	}
	
	static public void home() {
		Util.redirect("/ForumJSF/faces/home.xhtml");
	}
	
	static public void quemSomos() {
		Util.redirect("/ForumJSF/faces/pages/quemSomos.xhtml");
	}
	
	static public void comentarios() {
		Util.redirect("comentarios.xhtml");
	}
	
	static public void perfil() {
		Util.redirect("/ForumJSF/faces/pages/perfilUser.xhtml");
	}
	
	static public void sair() {
		Util.redirect("/ForumJSF/faces/home.xhtml");
		Session.getInstance().invalidateSession();
	}
}
