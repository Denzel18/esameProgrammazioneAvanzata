package it.univpm.advancedcode.cri.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "manutenzioni")
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
    
    
    private Car veicolo; 
      

    /**
     * Getter veicolo che ha eseguito la manutenzione
	 * @return the veicolo
	 */
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manutenzioneVeicolo")
	public Car getVeicolo() {
		return veicolo;
	}
	/**
	 * Setter veicolo che ha eseguito la manutenzione
	 * @param veicolo
	 */
	public void setVeicolo(Car veicolo) {
		this.veicolo = veicolo;
	}
	/**
	 * Getter Costo Manutenzione 
	 * @return costoManutenzione
	 */
	@Column(name = "costoManutenzione")
	public float getCostoManutenzione() {
		return costoManutenzione;
	}
	/**
     * Getter Id Manutenzione
     * @return id 
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="manutenzione_id", unique = true)
	public long getId() {
		return id;
	}
	/**
	 * Getter Tipo Manutenzione 
	 * @return tipoManutenzione
	 */
	@Column(name = "tipoManutenzione")
	public String getTipoManutenzione() {
		return tipoManutenzione;
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
