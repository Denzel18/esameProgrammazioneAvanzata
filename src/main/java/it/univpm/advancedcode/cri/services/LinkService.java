package it.univpm.advancedcode.cri.services;

import java.util.List;

import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.Link;

public interface LinkService {
	Link create(String description, boolean hide, Documentazione documento, String link);
	
	Link create(String description, Documentazione documento, String link);
	
	void delete(Link link);

	List<Link> getAll();
	
	Link getById(long id);
	
	List<Link> getLinkByDocumentazione(Documentazione documento);
	
	Link update(Link link);
}
