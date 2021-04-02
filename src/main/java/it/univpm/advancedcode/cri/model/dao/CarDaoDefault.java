package it.univpm.advancedcode.cri.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import it.univpm.advancedcode.cri.model.entities.Car;

@Repository("carDao")
public class CarDaoDefault extends DefaultDao implements CarDao {

	/**
	 * Car creator 
	 */
	@Override 
	public Car create (long Veicolo_ID, String targa,String marca, String modello, String numeroTelaio, int massa, 
			String destinazioneUso, int numeroAssi, String alimentazione) {
		Car car = new Car();
		car.setId(Veicolo_ID);
		car.setTarga(targa);
		car.setMarca(marca);
		car.setModello(modello);
		car.setNumeroTelaio(numeroTelaio);
		car.setMassa(massa);
		car.setDestinazioneUso(destinazioneUso);
		car.setAlimentazione(alimentazione);
		this.getSession().save(car);
		return car; 
	}

	/**
	 * Metodo per eliminare un veicolo
	 * @param veicolo : veicolo da eliminare
	 * 
	 */
	@Override
	public void delete(Car car) {
		this.getSession().delete(car);
	}
	
	/**
	 * Metodo per restituire un veicolo data la targa
	 * @param targa : targa del veicolo
	 * @return veicolo con quella targa
	 */
	@Override
	public Car getByTarga(String targa) {
		return (Car) getSession().createQuery("from Car c where c.targa="+"'"+targa+"'").uniqueResult();
		//return getSession().find(Car.class, targa);
	}

	@Override
	public Car getById(long id) {
		return (Car) getSession().find(Car.class, id);
	}


	/**
	 * Metodo per restituire la lista di tutti i veicoli
	 * @return lista di tutti i veicoli
	 */
	@Override
	public List<Car> getParcoMezzi() {
		List<Car> parcoMezzi = getSession().createQuery("from Car c", Car.class).getResultList();
		return parcoMezzi ; 
	}
	
	
	/**
	 * Metodo per aggiornare un veicolo
	 * @param car : veicolo da aggiornare
	 * @return car veicolo aggiornato
	 */
	@Override
	public Car update(Car car) {
		return (Car)this.getSession().merge(car);
	}
	
}
