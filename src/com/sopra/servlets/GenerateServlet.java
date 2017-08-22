package com.sopra.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.IAdminDAO;
import com.sopra.model.Admin;


@WebServlet("/")
public class GenerateServlet extends HttpServlet {

	@EJB(name="adminHibernateDAO")
	private IAdminDAO adminHibernateDAO;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Admin admin = new Admin();
		admin.setNom("test");
		admin.setPrenom("test");
		admin.setPassword("test");
		admin.setUsername("test");
		
		adminHibernateDAO.save(admin);
	}

}
