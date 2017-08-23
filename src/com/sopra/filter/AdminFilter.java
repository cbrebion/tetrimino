package com.sopra.filter;

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

//@WebFilter("/admin/*")
public class AdminFilter implements Filter {

	private static final String VUE_ERREUR	= "/tetrimino/accesNonAutorise";

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// L'utilisateur est ADMIN
		if (request.getSession().getAttribute("admin") != null) {
			chain.doFilter(request, response);
		}
		// L'utilisateur n'est pas ADMIN
		else {
			response.sendRedirect(VUE_ERREUR);
		}
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

}
