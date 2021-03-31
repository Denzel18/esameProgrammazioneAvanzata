package it.univpm.advancedcode.cri.model.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cars")
@NamedQueries({
	@NamedQuery(
			name = "Car.getByTarga",
			query = "FROM Car WHERE Car.TARGA = :targa"
			)
})
public class Car implements Serializable {

	private long veicoloID; 
	private String targa;
	private String marca; 
	private String modello; 
	private String numeroTelaio; 
	private int massa; 
	private String destinazioneUso; 
	private int numeroAssi; 
	private String alimentazione; 


	//----- ID VEICOLO -----
	
	/**
	 * Getter ID Veicolo
	 * @return the veicolo_id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "veicolo_id", unique = true)
	public long getVeicoloID() {
		return veicoloID;
	}
	/**
	 * Setter ID Veicolo
	 * @param veicolo_id the veicolo_id to set
	 */
	public void setVeicoloID(long veicolo_id) {
		this.veicoloID = veicolo_id;
	}

	//---- GETTER VEICOLO

	/**
	 * Getter Tipologia Alimentazione del veicolo
	 * @return alimentazione
	 */
	@Column(name="alimentazione", nullable = false)
	public String getAlimentazione() {
		return alimentazione;
	}
	/**
	 * Getter Destinazione d'uso del veicolo
	 * @return destinazioneUso
	 */
	@Column(name="destinazioneUso", nullable = false)
	public String getDestinazioneUso() {
		return destinazioneUso;
	}

	/**
	 * Getter Marca del veicolo 
	 * @return marca 
	 */
	@Column(name="marca", nullable = false)
	public String getMarca() {
		return marca;
	}
	/**
	 * Getter Massa del veicolo
	 * @return massa
	 */
	@Column(name="massa", nullable = false)
	public int getMassa() {
		return massa;
	}
	/**
	 * Getter Modello del veicolo 
	 * @return modello
	 */
	@Column(name="modello", nullable = false)
	public String getModello() {
		return modello;
	}
	/**
	 * Getter Numero Assi del veicolo 
	 * @return numeroAssi
	 */
	@Column(name="numeroAssi", nullable = false)
	public int getNumeroAssi() {
		return numeroAssi;
	}
	/**
	 * Getter Numero Telaio del veicolo
	 * @return numeroTelaio
	 */
	@Column(name="numeroTelaio", nullable = false)
	public String getNumeroTelaio() {
		return numeroTelaio;
	}
	/**
	 * Getter Targa del veicolo 
	 * @return targa
	 */

	@Column(name="targa", nullable = false, length = 7, unique = true)
	public String getTarga() {
		return targa;
	}


	//-----SETTER VEICOLO 

	/**
	 * Setter Tipologia Alimentazione del veicolo
	 * @param alimentazione
	 */
	public void setAlimentazione(String alimentazione) {
		this.alimentazione = alimentazione;
	}

	/**
	 * Setter Destinazione d'uso del veicolo
	 * @param destinazioneUso
	 */
	public void setDestinazioneUso(String destinazioneUso) {
		this.destinazioneUso = destinazioneUso;
	}

	/**
	 * Setter Marca del veicolo 
	 * @param marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Setter Massa del veicolo
	 * @param massa
	 */
	public void setMassa(int massa) {
		this.massa = massa;
	}

	/** 
	 * Setter Modello del veicolo 
	 * @param modello
	 */
	public void setModello(String modello) {
		this.modello = modello;
	}

	/**
	 * Setter Numero Assi del veicolo
	 * @param numeroAssi
	 */
	public void setNumeroAssi(int numeroAssi) {
		this.numeroAssi = numeroAssi;
	}

	/**
	 * Setter Numero Telaio del veicolo 
	 * @param numeroTelaio
	 */
	public void setNumeroTelaio(String numeroTelaio) {
		this.numeroTelaio = numeroTelaio;
	}

	/**
	 * Setter Targa del veicolo
	 * @param targa
	 */
	public void setTarga(String targa) {
		this.targa = targa;
	}


}