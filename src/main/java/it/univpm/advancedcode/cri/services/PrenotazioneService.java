package it.univpm.advancedcode.cri.services;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.model.entities.User;

public interface PrenotazioneService {

	Prenotazione create  (long id,Date dataInzio, Date dataFine, 
			Time oraInzio, Time oraFine, 
			String descrizione, User utente);
	
	void delete(Prenotazione prenotazione);
	
	List<Prenotazione> getAll(); 
	
	
		
	Prenotazione getById(long id);
	
	List <Prenotazione> getPrenotazioneByUtente(String username);
	
	Prenotazione update(Prenotazione prenotazione);
	 
}
