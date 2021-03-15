package it.univpm.advancedcode.cri.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import it.univpm.advancedcode.cri.model.entities.Allegato;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.User;

public interface DocumentazioneService {
    Documentazione create(String title, User autoreUtente, String descrizione, Date dataScadenza, float costo, Set<Car> cars);

    Documentazione create(String title, User autoreUtente, String descrizione, Date dataScadenza, float costo,
    	    Set<Allegato> allegati, Set<Car> cars);

    void delete(Documentazione documento);

    List<Documentazione> getAll();
    
    Documentazione getById(long id);

    Documentazione getByTitle(String title);

    Documentazione update(Documentazione documento);

}
