package it.univpm.advancedcode.cri.model.dao;

import java.util.List;

import org.hibernate.Session;

import it.univpm.advancedcode.cri.model.entities.Car;


public interface CarDao {
	
	Car create (String targa,String marca, String modello, String numeroTelaio, int massa, 
			String destinazioneUso, int numeroAssi, String alimentazione);

	void delete(Car car);
	
	Car getByTarga(String targa);
	
	List<Car> getParcoMezzi();
	
	Session getSession();
	
	void setSession(Session session);
	
	Car update(Car car); 
		
}
