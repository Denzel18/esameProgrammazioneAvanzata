package it.univpm.advancedcode.cri.services;

import java.time.*;
import java.util.List;
import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.model.entities.Car;

public interface PrenotazioneService {

	Prenotazione create  (long id,LocalDate dataInzio, LocalDate dataFine, 
			LocalTime oraInzio, LocalTime oraFine, 
			String descrizione, Car car , User utente);
	
	void delete(Prenotazione prenotazione);
	
	List<Prenotazione> getAll(); 
	
	Prenotazione getById(long id);
	
	List <Prenotazione> getPrenotazioneByUtente(String username);
	
	Prenotazione update(Prenotazione prenotazione);

}
