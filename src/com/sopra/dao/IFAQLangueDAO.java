package com.sopra.dao;

import java.util.List;

import com.sopra.model.FAQLangue;

public interface IFAQLangueDAO extends IDAO<FAQLangue> {
	public List<FAQLangue> findAllLanguage(String lang);
}
