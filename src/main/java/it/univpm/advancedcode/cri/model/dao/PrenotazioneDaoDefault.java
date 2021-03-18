package it.univpm.advancedcode.cri.model.dao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.model.entities.User;

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
	public Prenotazione create  (long id,Date dataInzio, Date dataFine, Time oraInzio, Time oraFine, 
			String descrizione, User utente) {
		Prenotazione prenotazione = new Prenotazione();
		prenotazione.setId(id);
		prenotazione.setDataInzio(dataInzio);
		prenotazione.setDataFine(dataFine);
		prenotazione.setOraInzio(oraInzio);
		prenotazione.setOraFine(oraFine);
		prenotazione.setDescrizione(descrizione);
		prenotazione.setUtente(utente);
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
	 * @return lista di tutte le manutenzioni
	 */
	@Override
	public List<Prenotazione> getAll() {
		return getSession().
				createQuery("from Prenotazioni", Prenotazione.class).
				getResultList();
	}

	/**
	 * Metodo per restituire una Prenotazione da un id
	 * @param id 
	 * @return Prenotazione
	 */
	@Override
	public Prenotazione getById(long id) {
		return getSession().find(Prenotazione.class, id);
	}

	public List<Prenotazione> getPrenotazioneByUtente(String username) {

		return getSession().
				createQuery(
						"select c.* "
								+ "from Prenotazioni p, User u "
								+ "where p.user = u.username"
								+ "order by p.id desc", Prenotazione.class).
				getResultList();
	}

	/**
	 * Metodo per aggiornare un Prenotazione
	 * @param allegato: manutenzioe da aggiornare
	 * 
	 * @return Prenotazione 
	 */
	@Override
	public Prenotazione update(Prenotazione prenotazione) {
		return (Prenotazione)this.getSession().merge(prenotazione);
	}


}
