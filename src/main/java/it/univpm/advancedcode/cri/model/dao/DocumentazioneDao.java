package it.univpm.advancedcode.cri.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import it.univpm.advancedcode.cri.model.entities.Allegato;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.User;

public interface DocumentazioneDao {
	
	Documentazione create(String title, User autoreUtente, String descrizione, Date dataScadenza, float costo, Car car);

	Documentazione create(String title, User autoreUtente, String descrizione, Date dataScadenza, float costo,
                Allegato allegato, Car car);

    void delete(Documentazione documento);

    List<Documentazione> getAll();

    Documentazione getById(long id);

    Documentazione getByTitle(String title);
    
    Session getSession();

    void setSession(Session session);

    Documentazione update(Documentazione documento);
}
