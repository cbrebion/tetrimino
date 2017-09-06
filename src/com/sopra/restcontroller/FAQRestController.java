package com.sopra.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.dao.IFAQLangueDAO;
import com.sopra.model.FAQLangue;

@RestController
@RequestMapping("/faq")
public class FAQRestController {
	@Autowired
	IFAQLangueDAO faqLangueHibernateDAO;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<FAQLangue>> getAll() {
		return new ResponseEntity<List<FAQLangue>>(this.faqLangueHibernateDAO.findAll(), HttpStatus.OK);
	}
}
