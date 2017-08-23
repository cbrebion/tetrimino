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

	private static final String VUE_LOGIN	= "accueil";

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		/* Non-filtrage des ressources statiques */
        String chemin = request.getRequestURI().substring( request.getContextPath().length() );
        if ( chemin.startsWith( "inc" ) || chemin.startsWith("css") || chemin.startsWith("img") || chemin.startsWith("fonts") || chemin.startsWith("js") ) {
            chain.doFilter( request, response );
            return;
        }

		// Demande d'accès à l'accueil
		if (request.getRequestURI().equals("/tetrimino/accueil")) {
			chain.doFilter(request, response);
			return;
		}
		// Demande d'accès à une autre page
		else {
			if (request.getSession().getAttribute("joueur") != null || request.getSession().getAttribute("admin") != null) {
				chain.doFilter(request, response);
				return;
			} else {
				response.sendRedirect(VUE_LOGIN);
			}
		}
	}

	@Override
	public void destroy() {
		//Filter.super.destroy();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//Filter.super.init(filterConfig);
	}

}
