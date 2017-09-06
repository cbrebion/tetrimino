package com.sopra.jsfcontroller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sopra.dao.IFAQDAO;
import com.sopra.dao.IFAQLangueDAO;
import com.sopra.dao.ILangueDAO;
import com.sopra.model.FAQ;
import com.sopra.model.FAQLangue;
import com.sopra.model.Langue;

@Controller
public class FaqController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IFAQLangueDAO faqLangueHibernateDAO;
	
	@Autowired
	private ILangueDAO langueHibernateDAO;
	
	@Autowired
	private IFAQDAO faqHibernateDAO;
	
	private List<FAQLangue> faqs;
	private List<Langue> langues;
	private List<FAQ> faqsBD;
	private Integer langueChoisie;
	private Integer faqChoisie;
	private FAQLangue faqLangue;
	
	
	@PostConstruct
	public void init() {
		this.faqs = faqLangueHibernateDAO.findAll();
		this.langues = langueHibernateDAO.findAll();
		this.faqsBD = faqHibernateDAO.findAll();
		this.faqLangue = new FAQLangue();
	}
	
	
	public String save() {
		FAQ f = faqHibernateDAO.find(this.faqChoisie);
		Langue l = langueHibernateDAO.find(this.langueChoisie);
		
		faqLangue.setLangue(l);
		faqLangue.setFaq(f);
		
		faqLangue = faqLangueHibernateDAO.save(faqLangue);
		
		return "faq";
	}

	public List<FAQLangue> getFaqs() {
		return faqs;
	}

	public void setFaqs(List<FAQLangue> faqs) {
		this.faqs = faqs;
	}

	public List<Langue> getLangues() {
		return langues;
	}

	public List<FAQ> getFaqsBD() {
		return faqsBD;
	}

	public Integer getLangueChoisie() {
		return langueChoisie;
	}

	public void setLangues(List<Langue> langues) {
		this.langues = langues;
	}

	public void setFaqsBD(List<FAQ> faqsBD) {
		this.faqsBD = faqsBD;
	}

	public void setLangueChoisie(Integer langueChoisie) {
		this.langueChoisie = langueChoisie;
	}

	public FAQLangue getFaqLangue() {
		return faqLangue;
	}

	public void setFaqLangue(FAQLangue faqLangue) {
		this.faqLangue = faqLangue;
	}


	public Integer getFaqChoisie() {
		return faqChoisie;
	}


	public void setFaqChoisie(Integer faqChoisie) {
		this.faqChoisie = faqChoisie;
	}

}
