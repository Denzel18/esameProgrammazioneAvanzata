package it.univpm.advancedcode.cri.model.dao;

import java.util.List;
import org.hibernate.Session;
import it.univpm.advancedcode.cri.model.entities.Manutenzione;
import it.univpm.advancedcode.cri.model.entities.Car;

public interface ManutenzioneDao {
	
	Manutenzione create (long id, String tipoManutenzione, float costoManutenzione, Car car) ;

	void delete(Manutenzione manutezione);
	
	List<Manutenzione> getAll();
	
	Manutenzione getById(long id);
	
	Session getSession();
	
	void setSession(Session session);
	
	Manutenzione update(Manutenzione manutezione); 
	
}
