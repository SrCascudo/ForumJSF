package br.cascuda.forum.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cascuda.forum.model.UserServer;

@WebFilter(filterName = "SecurytyFilter", urlPatterns = "/faces/pages/*")
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String endereco = servletRequest.getRequestURI();
		System.out.println(endereco);// IMPRIME ENDEROÇO DA PAGINA

		HttpSession session = servletRequest.getSession(false);
		UserServer usuario = null;

		// --------------------------------------------------------
		// ----- VERIFICA SE A SESSAO COM O USUARIO ESTA NULA -----
		if (session != null) {
			usuario = (UserServer) session.getAttribute("connect");
		}
		// --------------------------------------------------------
		// --------------------------------------------------------

		if (usuario == null) {
			((HttpServletResponse) response).sendRedirect("/ForumJSF/faces/home.xhtml");
		} else {
			// CONTINUA A EXECUCAO INDO PARA A PAGINA REQUISITADA
			chain.doFilter(request, response);
			return;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
		System.out.println("SecurityFilter Iniciado.");
	}
}
