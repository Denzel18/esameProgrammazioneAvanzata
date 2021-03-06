package it.univpm.advancedcode.cri.services;

import java.time.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.advancedcode.cri.model.dao.PrenotazioneDao;
import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.User;

@Service("prenotazioneService")
public class PrenotazioneServiceDefault implements PrenotazioneService {
	
	private PrenotazioneDao prenotazioneRepository;

	/**
	 * Funzione per creare una prenotazione
	 * @param id 
	 * @param dataInizio
	 * @param dataFine
	 * @param oraInizio
	 * @param oraFine
	 * @param descrizione
	 * @param car
	 * @param user
	 */
	@Transactional
	@Override
	public Prenotazione create(long id,LocalDate dataInzio, LocalDate dataFine, LocalTime oraInzio, LocalTime oraFine, 
			String descrizione, Car car, User user) {
		return this.prenotazioneRepository.create(id, dataInzio,  dataFine, oraInzio,  oraFine, descrizione, car, user);
	}

	/**
	 * Funzione per cancellare un prenotazione specifico
	 * @param prenotazione prenotazione da cancellare
	 */
	@Transactional
	@Override
	public void delete(Prenotazione prenotazione) {
		this.prenotazioneRepository.delete(prenotazione);
		
	}

    /**
	 * Funzione per ritornare la lista di tutti i tag
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Prenotazione> getAll() {
		return this.prenotazioneRepository.getAll();
	}
	/**
     * Funzione per cercare un particolare manutenzione specificandone l'ID.
     *
     * @param id id del manutenzione da cercare
     * @return manutenzione
     */
    @Transactional(readOnly = true)
    @Override
    public Prenotazione getById(long id) {
        return this.prenotazioneRepository.getById(id);
    }

	/**
	 * Funzione per trovare un tag dal name
	 * 
	 * @param name nome del tag
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Prenotazione> getPrenotazioneByUtente(String username){
		return this.prenotazioneRepository.getPrenotazioneByUtente(username);
	}
	
    /**
     * Setter per la propriet?? riferita al DAO dell'entit?? Tag.
     *
     * @param tagRepository DAO dell'entit?? Tag da settare
     */
	@Autowired
	public void setPrenotazioneRepository(PrenotazioneDao prenotazioneRepository) {
	    this.prenotazioneRepository = prenotazioneRepository;
	}
	
    /**
     * Funzione per aggiornare prenotazione
     *
     * @param prenotazione
     * @return prenotazione aggiornata
     */
    @Transactional
    @Override
    public Prenotazione update(Prenotazione prenotazione) {
        return this.prenotazioneRepository.update(prenotazione);
    }

}
