package it.univpm.advancedcode.cri.services;

import java.time.*;
import java.util.List;
import it.univpm.advancedcode.cri.model.entities.Documentazione;


public interface DocumentazioneService {
	
	Documentazione create(String title, String descrizione, LocalDate dataScadenza, float costo);
	
    void delete(Documentazione documento);

    List<Documentazione> getAll();
    
    Documentazione getById(long id);

    Documentazione getByTitle(String title);

    Documentazione update(Documentazione documento);

}
