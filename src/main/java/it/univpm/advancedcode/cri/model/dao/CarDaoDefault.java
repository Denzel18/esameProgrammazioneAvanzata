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
		car.setVeicoloID(Veicolo_ID);
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
	 * Metodo per eliminare un allegato
	 * @param allegato: allegato da eliminare
	 * 
	 */
	@Override
	public void delete(Car car) {
		this.getSession().delete(car);
	}
	
	/**
	 * Metodo per restituire un allegato da un id
	 * @param id: id dell'allegato da cercare 
	 * @return allegato con id specificato
	 */
	@Override
	public Car getByTarga(String targa) {
		return (Car) getSession().getNamedQuery("Car.getCarByTarga").setParameter("targa", targa).
                uniqueResult();
		//return getSession().find(Car.class, targa);
	}

	/**
	 * Metodo per restituire la lista di tutti gli allegati
	 * @return lista di tutti gli allegati
	 */
	@Override
	public List<Car> getParcoMezzi() {
		List<Car> parcoMezzi = getSession().createQuery("from Car c").list();
		return parcoMezzi ; 
		//return getSession().createQuery("select car from cars as c order by c.targa desc", Car.class).getResultList();
		 //return getSession().
	     //           createQuery("from car a order by a.targa desc", Car.class).
	     //           getResultList();
	}
	
	
	/**
	 * Metodo per aggiornare un post
	 * @param allegato: allegato da aggiornare
	 * 
	 * @return allegato aggiornato
	 */
	@Override
	public Car update(Car car) {
		return (Car)this.getSession().merge(car);
	}
	
}
