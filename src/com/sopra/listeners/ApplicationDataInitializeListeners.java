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
		// TODO Auto-generated method stub
		System.out.println("Initialisation du contexte");	

		List<Tetrimino> tetriminos = new ArrayList<Tetrimino>();
	}
}

