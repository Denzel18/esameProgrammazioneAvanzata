package it.univpm.advancedcode.cri.model.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import it.univpm.advancedcode.cri.model.entities.Manutenzione;
import it.univpm.advancedcode.cri.model.entities.Car;

@Repository("manutenzioneDao")
public class ManutenzioneDaoDefault extends DefaultDao implements ManutenzioneDao {
	
	/**
	 * Metodo per creare una manutenzione 
	 * @param id id della manutenzione 
	 * @param tipoManutenzione tipologia manutenzione "Straordinaria" o "Ordinaria"
	 * @param costoManutenzione costo manutenzione
	 * @param car veicolo soggetto a manutenzione
	 */
	@Override
	public Manutenzione create (long id, String tipoManutenzione, float costoManutenzione, Car car) {
		Manutenzione man = new Manutenzione(); 
		man.setId(id);
		man.setTipoManutenzione(tipoManutenzione);
		man.setCostoManutenzione(costoManutenzione);
		man.setVeicolo(car);
        this.getSession().save(man);
		return man; 
	}


	/**
	 * Metodo per eliminare una manutenzione
	 * @param manutenzione da eliminare
	 */
	@Override
	public void delete(Manutenzione manutenzione) {
		this.getSession().delete(manutenzione);

	}
	
	/**
	 * Metodo per restituire la lista di tutte le manutenzioni
	 * @return lista di tutte le manutenzioni
	 */
	@Override
	public List<Manutenzione> getAll() {
		 return getSession().
	                createQuery("from Manutenzione m", Manutenzione.class).
	                getResultList();
	}

	/**
	 * Metodo per restituire una manutenzione dato un id
	 * @param id della manutenzione
	 * @return manutenzione
	 */
	@Override
	public Manutenzione getById(long id) {
		return getSession().find(Manutenzione.class, id);
	}
	
	/**
	 * Metodo per aggiornare un manutenzione
	 * @param manutenzione : manutenzione da aggiornare
	 * @return manutenzione 
	 */
	@Override
	public Manutenzione update(Manutenzione manutenzione) {
		return (Manutenzione)this.getSession().merge(manutenzione);
	}


}
