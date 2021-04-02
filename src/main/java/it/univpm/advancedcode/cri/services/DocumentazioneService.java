package it.univpm.advancedcode.cri.services;

import java.time.*;
import java.util.List;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.model.entities.Allegato;
import it.univpm.advancedcode.cri.model.entities.Car;


public interface DocumentazioneService {
	
	Documentazione create(String title, User utente, String descrizione, LocalDate dataScadenza, float costo, Car car);

	Documentazione create(String title,  User utente, String descrizione, LocalDate dataScadenza, float costo,
                Allegato allegato, Car car);
	
    void delete(Documentazione documento);

    List<Documentazione> getAll();
    
    Documentazione getById(long id);

    Documentazione getByTitle(String title);

    Documentazione update(Documentazione documento);

}
