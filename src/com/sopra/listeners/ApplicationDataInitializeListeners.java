package com.sopra.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import com.sopra.model.Tetrimino;

@WebListener
public class ApplicationDataInitializeListeners implements javax.servlet.ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Destruction du contexte");	
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		List<Tetrimino> tetriminos = new ArrayList<Tetrimino>();
		Tetrimino tetri;
		tetri = new Tetrimino("L", "Vert");
		tetriminos.add(tetri);
		tetri = new Tetrimino("T", "Rouge");
		tetriminos.add(tetri);
		tetri = new Tetrimino("S", "Bleu");
		tetriminos.add(tetri);
		tetri = new Tetrimino("L", "Orange");
		tetriminos.add(tetri);
		
		event.getServletContext().setAttribute("tetriminos", tetriminos);
	}
}

