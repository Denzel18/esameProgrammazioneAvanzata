package it.univpm.advancedcode.cri.services;

import java.util.List;

import it.univpm.advancedcode.cri.model.entities.Car;

public interface CarService {
	
		Car create(long Veicolo_ID, String targa,String marca, String modello, String numeroTelaio, int massa, 
				String destinazioneUso, int numeroAssi, String alimentazione);
	    
	    void delete(Car car);
	    
	    void delete(String targa);
	    
	    List<Car> getAll();
	    
	    Car getByTarga(String name);
	    
	    Car update(Car car);

}
