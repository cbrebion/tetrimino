package com.sopra.jsfcontroller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sopra.dao.IFAQDAO;
import com.sopra.dao.IFAQLangueDAO;
import com.sopra.dao.ILangueDAO;
import com.sopra.model.FAQ;
import com.sopra.model.FAQLangue;
import com.sopra.model.Langue;

@Controller
@Scope("request")
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
	
	private Integer idFAQ;
	
	
	@PostConstruct
	public void init() {
		this.faqs = faqLangueHibernateDAO.findAll();
		this.langues = langueHibernateDAO.findAll();
		this.faqsBD = faqHibernateDAO.findAll();
		this.faqLangue = new FAQLangue();

		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse resp = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		if (req.getParameter("idFAQLangue") != null) {
			idFAQ= Integer.parseInt(req.getParameter("idFAQLangue"));
			this.faqLangue = faqLangueHibernateDAO.find(idFAQ);
		}
		
		if (req.getParameter("lang") != null) {
			for (Cookie cookie : req.getCookies()) {
				if (cookie.getName().equals("lang")) {
					cookie.setValue(req.getParameter("lang"));
					resp.addCookie(cookie);
				}
			}
		}
	}
	
	
	public String save() {
		FAQ f = faqHibernateDAO.find(this.faqChoisie);
		Langue l = langueHibernateDAO.find(this.langueChoisie);
		
		faqLangue.setLangue(l);
		faqLangue.setFaq(f);
		
		faqLangue = faqLangueHibernateDAO.save(faqLangue);
		
		return "faq?faces-redirect=true";
	}
	
	public String modifier() {
		System.out.println(idFAQ);
		
		FAQLangue fl = faqLangueHibernateDAO.find(idFAQ);
		
		FAQ f = faqHibernateDAO.find(this.faqChoisie);
		Langue l = langueHibernateDAO.find(this.langueChoisie);
		
		this.faqLangue.setId(fl.getId());
		this.faqLangue.setLangue(l);
		this.faqLangue.setFaq(f);
		
		this.faqLangue = faqLangueHibernateDAO.save(this.faqLangue);
		return "faq?faces-redirect=true";
	}
	
	public String delete(int id) {
		System.out.println(id);
		this.faqLangue = faqLangueHibernateDAO.find(id);
		System.out.println(faqLangue.getQuestion());
		return "faq.xhtml";
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


	public Integer getIdFAQ() {
		return idFAQ;
	}


	public void setIdFAQ(Integer idFAQ) {
		this.idFAQ = idFAQ;
	}

}
