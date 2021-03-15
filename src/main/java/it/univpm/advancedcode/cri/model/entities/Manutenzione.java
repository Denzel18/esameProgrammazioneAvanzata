package it.univpm.advancedcode.cri.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity(name = "Manutenzione")
@Table(name = "Manutenzioni", uniqueConstraints = {
        @UniqueConstraint(columnNames = "MANUTENZIONE_ID")})
@NamedQueries({
    @NamedQuery(
            name = "Manutenzione.getById",
            query = "FROM Manutenzione WHERE Manutenzione.MANUTENZIONE_ID = :ID"
    )
})
public class Manutenzione implements Serializable{
	
	private long id;
    private String tipoManutenzione; // straordinaria o regolare
    private float costoManutenzione;
    private Set<Car> cars = new HashSet<Car>();
    
    public void addCars(Car car) {
		this.cars.add(car);
		car.getManutenzioni().add(this);
	}
    
	@ManyToMany(mappedBy="manutenzioni")
	public Set<Car> getCars(){
		return this.cars;
	}
	/**
	 * Getter Costo Manutenzione 
	 * @return costoManutenzione
	 */
	@Column(name = "COSTO_MANUTENZIONE")
	public float getCostoManutenzione() {
		return costoManutenzione;
	}
	/**
     * Getter Id Manutenzione
     * @return id 
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MANUTENZIONE_ID", unique = true)
	public long getId() {
		return id;
	}
	/**
	 * Getter Tipo Manutenzione 
	 * @return tipoManutenzione
	 */
	@Column(name = "TIPO_MANUTENZIONE")
	public String getTipoManutenzione() {
		return tipoManutenzione;
	}
	public void setCars(Set<Car> cars) {
		this.cars=cars;
	} 
    
	/**
	 * Setter Costo Manutenzione
	 * @param costoManutenzione
	 */
	public void setCostoManutenzione(float costoManutenzione) {
		this.costoManutenzione = costoManutenzione;
	}
	
	/**
	 * Setter Id Manutenzione
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Setter Tipo Manutenzione 
	 * @param tipoManutenzione
	 */
	public void setTipoManutenzione(String tipoManutenzione) {
		this.tipoManutenzione = tipoManutenzione;
	}
}
