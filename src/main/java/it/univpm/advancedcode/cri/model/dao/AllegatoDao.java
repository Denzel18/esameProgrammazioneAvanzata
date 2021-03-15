package it.univpm.advancedcode.cri.model.dao;

import java.util.List;

import org.hibernate.Session;

import it.univpm.advancedcode.cri.model.entities.Allegato;

public interface AllegatoDao {
	void delete(Allegato attachment);

	List<Allegato> getAll();
	
	Allegato getById(long id);
	
	Session getSession();
	
	void setSession(Session session);
	
	Allegato update(Allegato attachment);
}
