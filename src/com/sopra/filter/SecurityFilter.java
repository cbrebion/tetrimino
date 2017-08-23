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

@WebFilter("/*")
public class SecurityFilter implements Filter {

	private static final String VUE_LOGIN	= "/tetrimino/accueil";

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// Demande d'accès à l'accueil
		if (request.getRequestURI().equals("accueil")) {
			chain.doFilter(request, response);
		}
		// Demande d'accès à une autre page
		else {
			if (request.getSession().getAttribute("joueur") == null || request.getSession().getAttribute("admin") == null) {
				response.sendRedirect(VUE_LOGIN);
			} else {
				chain.doFilter(request, response);
			}
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
