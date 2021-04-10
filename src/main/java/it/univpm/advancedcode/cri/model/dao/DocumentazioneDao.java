package it.univpm.advancedcode.cri.model.dao;

import java.time.*;
import java.util.List;
import org.hibernate.Session;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Allegato;


public interface DocumentazioneDao {
		
	Documentazione create(long id, String titolo,  User utente, String descrizione, LocalDate dataScadenza, float costo, Car car);

	Documentazione create(long id, String titolo, User utente, String descrizione, LocalDate dataScadenza, float costo, Allegato allegato, Car car);

    void delete(Documentazione documento);

    List<Documentazione> getAll();

    Documentazione getById(long id);

    Documentazione getByTitle(String title);
    
    Session getSession();

    void setSession(Session session);

    Documentazione update(Documentazione documento);
}
