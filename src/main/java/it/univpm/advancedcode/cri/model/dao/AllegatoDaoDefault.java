package it.univpm.advancedcode.cri.model.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import it.univpm.advancedcode.cri.model.entities.Allegato;
import it.univpm.advancedcode.cri.model.entities.Documentazione;

@Repository("allegatoDao")
public class AllegatoDaoDefault extends DefaultDao implements AllegatoDao {

	/**
	 * Metodo per restituire la lista di tutti gli allegati
	 * @return lista di tutti gli allegati
	 */
	@Override
	public List<Allegato> getAll() {
		 return getSession().
	                createQuery("from Allegato a", Allegato.class).
	                getResultList();
	}

	/**
	 * Metodo per restituire un allegato da un id
	 * @param id: id dell'allegato da cercare 
	 * @return allegato con id specificato
	 */
	@Override
	public Allegato getById(long id) {
		return getSession().find(Allegato.class, id);
	}
	
	/**
	 * Metodo per aggiornare un allegato
	 * @param allegato: allegato da aggiornare
	 * 
	 * @return allegato aggiornato
	 */
	@Override
	public Allegato update(Allegato allegato) {
		return (Allegato)this.getSession().merge(allegato);
	}

	/**
	 * Metodo per eliminare un allegato
	 * @param allegato: allegato da eliminare
	 * 
	 */
	@Override
	public void delete(Allegato allegato) {
		this.getSession().delete(allegato);

	}

}
