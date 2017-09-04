package com.sopra.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.dao.IJoueurDAO;
import com.sopra.dao.IPersonneDAO;
import com.sopra.generic.Tools;
import com.sopra.model.Joueur;
import com.sopra.model.Personne;

@RestController
@RequestMapping("/joueur")
public class JoueurRestController {
	@Autowired
	private IJoueurDAO joueurHibernateDAO;
	
	@Autowired
	private IPersonneDAO personneHibernateDAO;
	
	
	/**
	 * AJOUT JOUEUR
	 * @param joueur
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Joueur> add(@RequestBody Joueur joueur) {
		joueur = this.joueurHibernateDAO.save(joueur);
		
		return new ResponseEntity<>(joueur, HttpStatus.OK);
	}
	
	
	/**
	 * MODIFICATION JOUEUR
	 * @param joueurNew
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Joueur> modify(@RequestBody Joueur joueurNew,
			@PathVariable(value="id", required=true) Integer id) {
		
		Joueur joueur = this.joueurHibernateDAO.find(id);
		joueurNew.setId(id);
		
		try {
			Tools.copy(joueurNew, joueur);
			joueur = this.joueurHibernateDAO.save(joueur);
			
			return new ResponseEntity<Joueur>(joueur, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Joueur>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
	 * RECUPERATION DE TOUS LES JOUEURS
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Joueur>> getAll() {
		return new ResponseEntity<List<Joueur>>(this.joueurHibernateDAO.findAll(), HttpStatus.OK);
	}
	
	
	/**
	 * RECUPERATION D'UN JOUEUR PAR SON ID
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Joueur> getById(@PathVariable(value="id", required=true) Integer id) {
		return new ResponseEntity<Joueur>(this.joueurHibernateDAO.find(id), HttpStatus.OK);
	}
	
	
	/**
	 * CONNEXION D'UN JOUEUR
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="login/{username}", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Joueur> connect(@PathVariable(value="username", required=true) String username,
			@RequestParam(value="password", required=true) String password) {
				
			Personne personne = this.personneHibernateDAO.findByUsername(username);
			Joueur joueur;
			
			if (personne != null) {
				if (personne.getPassword().equals(password)) {
					if (personne.getType() == 2) {
						joueur = (Joueur) personne;
						
						System.out.println("EXISTE");
						return new ResponseEntity<Joueur>(joueur, HttpStatus.OK);
					}
					System.out.println("PAS JOUEUR");
				}
				System.out.println("MDP INCORRECT");
			}
			System.out.println("EXISTE PAS");
			// La personne n'existe pas en DB
			return new ResponseEntity<Joueur>(HttpStatus.BAD_REQUEST);
		}
}
