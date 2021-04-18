package it.univpm.advancedcode.cri.model.dao;

import java.time.*; 

import java.util.List;

import org.hibernate.tuple.UpdateTimestampGeneration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Repository;
import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.model.entities.Car;

import it.univpm.advancedcode.cri.model.dao.UserDao;


@Repository("prenotazioneDao")
public class PrenotazioneDaoDefault extends DefaultDao implements PrenotazioneDao {


	/**
	 * Metodo per la creazione di una nuova prenotazione 
	 * @param id 
	 * @param dataInizio 
	 * @param dataFine 
	 * @param oraInizio
	 * @param oraFine
	 * @return una prenotazione con le caratteristiche richieste 
	 */
	@Override 
	public Prenotazione create  (long id,LocalDate dataInzio, LocalDate dataFine, LocalTime oraInzio, LocalTime oraFine, 
			String descrizione, Car car , User utente) {
		Prenotazione prenotazione = new Prenotazione();
		prenotazione.setId(id);
		prenotazione.setDataInizio(dataInzio);
		prenotazione.setDataFine(dataFine);
		prenotazione.setOraInizio(oraInzio);
		prenotazione.setOraFine(oraFine);
		prenotazione.setDescrizione(descrizione);
		prenotazione.setUtente(utente);
		prenotazione.setVeicolo(car);
		this.getSession().save(prenotazione);
		return prenotazione;
	}

	/**
	 * Metodo per eliminare una Prenotazione
	 * @param Prenotazione
	 * 
	 */
	@Override
	public void delete(Prenotazione prenotazione) {
		this.getSession().delete(prenotazione);
	}

	/**
	 * Metodo per restituire la lista di tutte le prenotazioni
	 * @return lista di tutte le prenotazioni
	 */
	@Override
	public List<Prenotazione> getAll() {
		return getSession().
				createQuery("from Prenotazione p", Prenotazione.class).
				getResultList();
	}

	/**
	 * Metodo per restituire una Prenotazione dato un id
	 * @param id 
	 * @return Prenotazione
	 */
	@Override
	public Prenotazione getById(long id) {
		return getSession().find(Prenotazione.class, id);
	}

	
	/** 
	 * @param username
	 * @return List<Prenotazione>
	 */
	@Override
	public List<Prenotazione> getPrenotazioneByUtente(String username) {
		User utente = getSession().find(User.class, username);
		return (List<Prenotazione>) getSession().createQuery("from Prenotazione p where p.utente =:username").setParameter("username", utente).getResultList();
	}

	/**
	 * Metodo per aggiornare un Prenotazione
	 * @param prenotazione : prenotazione da aggiornare
	 * @return prenotazione 
	 */
	@Override
	public Prenotazione update(Prenotazione prenotazione) {
		return (Prenotazione)this.getSession().merge(prenotazione);
	}
}
