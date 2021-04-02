package it.univpm.advancedcode.cri.services;

import java.util.List;

import it.univpm.advancedcode.cri.model.entities.Allegato;

public interface AllegatoService {
	
	void delete(Allegato allegato);
	
	List<Allegato> getAll();

	Allegato getById(long id);
	
	Allegato update(Allegato allegato);

}

