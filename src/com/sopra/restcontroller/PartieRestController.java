package com.sopra.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.dao.IPartieDAO;
import com.sopra.model.Partie;


@RestController
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
	
	
	
}
