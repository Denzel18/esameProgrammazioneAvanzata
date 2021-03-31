package it.univpm.advancedcode.cri.model.dao;

import java.time.*;
import java.util.List;

import org.hibernate.Session;

import it.univpm.advancedcode.cri.model.entities.Prenotazione;
public interface PrenotazioneDao {
	
	Prenotazione create  (long id, LocalDate dataInzio, LocalDate dataFine, 
			LocalTime oraInzio, LocalTime oraFine, 
			String descrizione);

	void delete(Prenotazione prenotazione);
	
	List<Prenotazione> getAll();
	
	Prenotazione getById(long id);
	
	List<Prenotazione> getPrenotazioneByUtente(String username);
	
	Session getSession();
	
	void setSession(Session session); 
	
	Prenotazione update(Prenotazione prenotazione); 
}
    
	
