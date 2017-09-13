package com.sopra.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.dao.IPartieDAO;
import com.sopra.generic.Tools;
import com.sopra.model.Partie;
import com.sopra.model.PartieJSON;


@RestController
@CrossOrigin("*")
@RequestMapping(value="/partie")
public class PartieRestController {
	@Autowired
	private IPartieDAO partieHibernateDAO;
	
	
	/**
	 * AJOUT D'UNE PARTIE
	 * @param partie
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Partie> add(@RequestBody Partie partie) {
		partie = this.partieHibernateDAO.save(partie);
		
		return new ResponseEntity<Partie>(partie, HttpStatus.OK);
	}
	
	
	/**
	 * RECUPERATION DE TOUTES LES PARTIES
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<PartieJSON>> getAll() {
		List<Partie> parties = this.partieHibernateDAO.findAll();
		List<PartieJSON> partiesJSON = new ArrayList<PartieJSON>();
		
		for (Partie partie : parties) {
			partiesJSON.add(new PartieJSON(partie));
		}
		
		return new ResponseEntity<List<PartieJSON>>(partiesJSON, HttpStatus.OK);
	}
	
	
	/**
	 * RECUPERATION DE LA DERNIERE PARTIE
	 * @return
	 */
	@RequestMapping(value="last", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Partie> getLast() {
		return new ResponseEntity<Partie>(this.partieHibernateDAO.findLastCreated(), HttpStatus.OK);
	}
	
	
	/**
	 * MODIFICATION PARTIE
	 * @param partieNew
	 * @param id
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Partie> modify(@RequestBody Partie partieNew) {
		Partie joueur = this.partieHibernateDAO.find(partieNew.getId());
		
		try {
			Tools.copy(partieNew, joueur);
			joueur = this.partieHibernateDAO.save(joueur);
			
			return new ResponseEntity<Partie>(joueur, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Partie>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(value="pending", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Partie>> findPending() {
		return new ResponseEntity<List<Partie>>(this.partieHibernateDAO.findAllPending(), HttpStatus.OK);
	}
}
