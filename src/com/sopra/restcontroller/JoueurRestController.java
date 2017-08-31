package com.sopra.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.dao.IJoueurDAO;

@RestController
@RequestMapping("/joueur")
public class JoueurRestController {
	@Autowired
	private IJoueurDAO joueurHibernateDAO;
}
