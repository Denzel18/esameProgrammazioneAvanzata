package it.univpm.advancedcode.cri.model.dao;

import java.time.*;
import java.util.List;
import org.hibernate.Session;
import it.univpm.advancedcode.cri.model.entities.Documentazione;


public interface DocumentazioneDao {
		
	Documentazione create(String title, String descrizione, LocalDate dataScadenza, float costo);

    void delete(Documentazione documento);

    List<Documentazione> getAll();

    Documentazione getById(long id);

    Documentazione getByTitle(String title);
    
    Session getSession();

    void setSession(Session session);

    Documentazione update(Documentazione documento);
}
