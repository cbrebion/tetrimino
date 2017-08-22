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
		/*tetri = new Tetrimino(0, "plop", "Vert");
		tetriminos.add(tetri);
		tetri = new Tetrimino(1, "T", "Rouge");
		tetriminos.add(tetri);
		tetri = new Tetrimino(2, "S", "Bleu");
		tetriminos.add(tetri);
		tetri = new Tetrimino(3, "L", "Orange");
		tetriminos.add(tetri);*/
		
		event.getServletContext().setAttribute("tetriminos", tetriminos);
	}
}

