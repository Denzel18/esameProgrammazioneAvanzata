package it.univpm.advancedcode.cri.model.entities;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;


@Entity
@Table(name = "cars")
public class Car implements Serializable {

	private long id; 
	private String targa;
	private String marca; 
	private String modello; 
	private String numeroTelaio; 
	private int massa; 
	private String destinazioneUso; 
	private int numeroAssi; 
	private String alimentazione; 

	//---- RELATIONS -------
	private Set<Documentazione> documentazioni = new HashSet<Documentazione>();

	private Set<Manutenzione> manutenzioni = new HashSet<Manutenzione>();

	private Set<Prenotazione> prenotazioni = new HashSet<Prenotazione>();

	private Set<User> utenti = new HashSet<User>();



	//-----DOCUMENTAZIONE-----
	/**
	 * Getter documentazioni
	 * @return
	 */
	@OneToMany(mappedBy = "veicolo", cascade = CascadeType.ALL)
	public Set<Documentazione> getDocumentazioni() {
		return this.documentazioni;
	}
	/**
	 * Setter documentazioni
	 * @param documentazioni documentazioni del veicolo da settare
	 */
	public void setDocumentazioni(Set<Documentazione> documentazioni) {
		this.documentazioni = documentazioni;
	}	

	//-----MANUTENZIONE-----
	/**
	 * Getter manutenzioni 
	 * @return manutenzioni manutenzioni del veicolo 
	 */
	@OneToMany(mappedBy = "veicolo", cascade = CascadeType.ALL)
	public Set<Manutenzione> getManutenzioni() {
		return this.manutenzioni;
	}
	/**
	 * Setter per la proprietà manutenzioni.
	 * @param manutenzioni manutenzioni del veicolo da settare
	 */
	public void setManutenzioni(Set<Manutenzione> manutenzioni) {
		this.manutenzioni = manutenzioni;
	}


	//-----PRENOTAZIONI-----
	/**
	 * Getter prenotazioni
	 * @return prenotazioni
	 */
	@OneToMany(mappedBy = "veicolo", cascade = CascadeType.ALL)
	public Set<Prenotazione> getPrenotazioni() {
		return this.prenotazioni;
	}
	/**
	 * Setter prenotazioni
	 * @param prenotazioni
	 */
	public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}


	//---UTENTI
	/**
	 * Getter utenti
	 * @return utenti 
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "cars_users",
	joinColumns = @JoinColumn(name = "veicolo_id", nullable = false),
	inverseJoinColumns = @JoinColumn(name = "username", nullable = false))
	public Set<User> getUtenti(){
		return this.utenti ; 
	}

	/**
	 * Setter utenti
	 * @param documentazioni documentazioni del veicolo da settare
	 */
	public void setUtenti(Set<User> carUsers) {
		this.utenti = carUsers;
	}

	//----- ID VEICOLO -----
	/**
	 * Getter ID Veicolo
	 * @return the veicolo_id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "veicolo_id", unique = true)
	public long getId() {
		return this.id;
	}
	/**
	 * Setter ID Veicolo
	 * @param veicolo_id the veicolo_id to set
	 */
	public void setId(long id) {
		this.id = id;
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