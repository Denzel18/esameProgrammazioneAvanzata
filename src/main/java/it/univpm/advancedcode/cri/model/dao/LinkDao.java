package it.univpm.advancedcode.cri.model.dao;

import java.util.List;

import org.hibernate.Session;

import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.Link;

public interface LinkDao {
	Link create(String description, boolean hide, Documentazione documento, String link);
	
	Link create(String description, Documentazione documento, String link);
	
	void delete(Link link);
	
	List<Link> getAll();

	Link getById(long id);
	
	Session getSession();
	
	void setSession(Session session);
	
	Link update(Link link);
}
