package com.sopra.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.dao.IScoreDAO;
import com.sopra.model.Score;

@RestController
@RequestMapping(value="/score")
public class ScoreRestController {

	@Autowired
	private IScoreDAO scoreHibernateDAO;
	
	
	/**
	 * RECUPERATION DU SCORE PAR SON ID
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Score> getById(@PathVariable(value="id", required=true) Integer id) {
		return new ResponseEntity<Score>(this.scoreHibernateDAO.find(id), HttpStatus.OK);
	}
	
	
	/**
	 * AJOUT D'UN SCORE
	 * @param score
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Score> add(@RequestBody Score score) {
		score = this.scoreHibernateDAO.save(score);
		
		return new ResponseEntity<Score>(score, HttpStatus.OK);
	}
}
