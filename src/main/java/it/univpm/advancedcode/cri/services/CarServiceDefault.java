package it.univpm.advancedcode.cri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.advancedcode.cri.model.dao.CarDao;
import it.univpm.advancedcode.cri.model.entities.Car;

@Service("carService")
public class CarServiceDefault implements CarService {
	
	private CarDao carRepository;
	
	/**
	 * Funzione per creare un veicolo
	 * 
	 * @param car
	 */

	@Transactional
	@Override
	public Car create(long Veicolo_ID, String targa,String marca, String modello, String numeroTelaio, int massa, 
			String destinazioneUso, int numeroAssi, String alimentazione) {
		
		return this.carRepository.create(Veicolo_ID, targa, marca, modello, numeroTelaio, massa, destinazioneUso, numeroAssi, alimentazione);
	}

	/**
	 * Funzione per elimiare un veicolo specificato
	 * 
	 * @param car
	 */
	@Transactional
	@Override
	public void delete(Car car) {
		 this.carRepository.delete(car);
	}
	
	/**
     * Funzione per eliminare un'archivio specificando il nome.
     *
     * @param name nome dell'archivio
     */

	@Transactional
	@Override
	public void delete(String targa) {
		Car car= this.getByTarga(targa);
        this.carRepository.delete(car);
	}

	/**
	 * Funzione per restituire la lista di tutti i mezzi 
	 * 
	 * @return lista con tutti i veicoli
	 */

	@Transactional(readOnly=true)
	@Override
	public List<Car> getAll() {
		return this.carRepository.getParcoMezzi();
	}
	
    /**
	 * Funzione per trovare un veicolo dalla targa
	 * 
	 * @param targa targa del veicolo
	 * @return veicolo ricercato 
	 */
	@Transactional(readOnly=true)
	@Override
	public Car getByTarga(String targa) {
		return this.carRepository.getByTarga(targa);
	}

	/**
	 * Funzione per trovare un veicolo dal ID
	 * 
	 * @param id id del veicolo
	 * @return veicolo ricercato 
	 */
	@Transactional(readOnly=true)
	@Override
	public Car getById(long veicolo_id) {
		return this.carRepository.getById(veicolo_id); 
	}
	
    /**
     * Setter per la propriet?? riferit?? al DAO dell'entit?? car
     *
     * @param carRepository DAO dell'entit?? car da settare
     */
    @Autowired
    public void setCarRepository(CarDao carRepository) {
        this.carRepository = carRepository;
    }
	
	 /**
     * Funzione per eliminare un veicolo specificando la targa
     *
     * @param targa 
     */

	@Transactional
	@Override
	public Car update(Car car) {
		return this.carRepository.update(car);
	}

}
