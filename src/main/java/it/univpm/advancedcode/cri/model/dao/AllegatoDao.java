package it.univpm.advancedcode.cri.model.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.Session;

import it.univpm.advancedcode.cri.model.entities.Allegato;
import it.univpm.advancedcode.cri.model.entities.Documentazione;

public interface AllegatoDao {
	void delete(Allegato attachment);

	List<Allegato> getAll();
	
	Allegato getById(long id);
	
	Session getSession();
	
	void setSession(Session session);
	
	Allegato update(Allegato attachment);
}
