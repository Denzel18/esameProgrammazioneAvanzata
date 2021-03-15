package it.univpm.advancedcode.cri.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity(name = "Car")
@Table(name = "Cars", uniqueConstraints = {
        @UniqueConstraint(columnNames = "TARGA")})
@NamedQueries({
	@NamedQuery(
			name = "Car.getByTarga",
			query = "FROM Car WHERE Car.TARGA = :targa"
			)
})
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;
	private String targa;
	private String marca; 
	private String modello; 
	private String numeroTelaio; 
	private int massa; 
	private String destinazioneUso; 
	private int numeroAssi; 
	private String alimentazione; 


	private Set<Documentazione> documentazioni = new HashSet<Documentazione>();
	private Set<Manutenzione> carManutenzioni = new HashSet<Manutenzione>();
	private Set<Prenotazione> carPrenotazioni = new HashSet<Prenotazione>();
	private Set<User> carUsers = new HashSet<User>();

	/**
	 * Metodo per aggiungere un documento al veicolo.
	 *
	 * @param documento documento da aggiungere
	 */
	public void addDocumentazione(Documentazione documento) {
		this.documentazioni.add(documento);
		documento.getCars().add(this);
	}
	/**
	 * Metodo per aggiungere un documento al veicolo.
	 *
	 * @param documento documento da aggiungere
	 */
	public void addManutenzione(Manutenzione manutenzione) {
		this.carManutenzioni.add(manutenzione);
		manutenzione.getCars().add(this);
	}
	/**
	 * Metodo per aggiungere un prenotazione al veicolo.
	 *
	 * @param prenotazione prenotazione da aggiungere
	 */
	public void addPrenotazione(Prenotazione prenotazione) {
		this.carPrenotazioni.add(prenotazione);
		prenotazione.getCars().add(this);
	}
	/**
	 * Metodo per aggiungere un documento al veicolo.
	 *
	 * @param documento documento da aggiungere
	 */
	public void addUtente(User user) {
		this.carUsers.add(user);
		user.getCars().add(this);
	}

	/**
	 * Getter Tipologia Alimentazione del veicolo
	 * @return alimentazione
	 */
	@Column(name="ALIMENTAZIONE", nullable = false)
	public String getAlimentazione() {
		return alimentazione;
	}
	/**
	 * Getter Destinazione d'uso del veicolo
	 * @return destinazioneUso
	 */
	@Column(name="DESTINAZIONE_USO", nullable = false)
	public String getDestinazioneUso() {
		return destinazioneUso;
	}
	@ManyToMany
	@JoinTable(name="cars_documentazioni", joinColumns={@JoinColumn(referencedColumnName="TARGA")}
	, inverseJoinColumns={@JoinColumn(referencedColumnName="DOCUMENTO_ID")})
	public Set<Documentazione> getDocumentazioni() {
		return this.documentazioni;
	}
	/**
	 * relation more to more car - manutenzioni
	 * 
	 */
	@ManyToMany
	@JoinTable(name="cars_manutenzioni", joinColumns={@JoinColumn(referencedColumnName="TARGA")}
	, inverseJoinColumns={@JoinColumn(referencedColumnName="MANUTENZIONE_ID")})
	public Set<Manutenzione> getManutenzioni() {
		return this.carManutenzioni;
	}
	/**
	 * Getter Marca del veicolo 
	 * @return marca 
	 */
	@Column(name="MARCA", nullable = false)
	public String getMarca() {
		return marca;
	}
	/**
	 * Getter Massa del veicolo
	 * @return massa
	 */
	@Column(name="MASSA", nullable = false)
	public int getMassa() {
		return massa;
	}
	/**
	 * Getter Modello del veicolo 
	 * @return modello
	 */
	@Column(name="MODELLO", nullable = false)
	public String getModello() {
		return modello;
	}
	/**
	 * Getter Numero Assi del veicolo 
	 * @return numeroAssi
	 */
	@Column(name="NUMERO_ASSI", nullable = false)
	public int getNumeroAssi() {
		return numeroAssi;
	}
	/**
	 * Getter Numero Telaio del veicolo
	 * @return numeroTelaio
	 */
	@Column(name="NUMERO_TELAIO", nullable = false)
	public String getNumeroTelaio() {
		return numeroTelaio;
	}
	@ManyToMany
	@JoinTable(name="cars_prenotazioni", joinColumns={@JoinColumn(referencedColumnName="TARGA")}
	, inverseJoinColumns={@JoinColumn(referencedColumnName="PRENOTAZIONE_ID")})
	public Set<Prenotazione> getPrenotazioni() {
		return this.carPrenotazioni;
	}
	/**
	 * Getter Targa del veicolo 
	 * @return targa
	 */
	@Id
	@Column(name="TARGA", nullable = false, length = 7, unique = true)
	public String getTarga() {
		return targa;
	}
	@ManyToMany
	@JoinTable(name="cars_utenti", joinColumns={@JoinColumn(referencedColumnName="TARGA")}
	, inverseJoinColumns={@JoinColumn(referencedColumnName="USERNAME")})
	public Set<User> getUtenti() {
		return this.carUsers;
	}

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
	 * Setter per la proprietà documentazioni.
	 *
	 * @param documentazioni documentazioni del veicolo da settare
	 */
	public void setDocumentazioni(Set<Documentazione> documentazioni) {
		this.documentazioni = documentazioni;
	}	
	/**
	 * Setter per la proprietà manutenzioni.
	 *
	 * @param manutenzioni manutenzioni del veicolo da settare
	 */
	public void setManutenzioni(Set<Manutenzione> carManutenzioni) {
		this.carManutenzioni = carManutenzioni;
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
	 * Setter per la proprietà prenotazioni.
	 *
	 * @param prenotazioni prenotazioni del veicolo da settare
	 */
	public void setPrenotazioni(Set<Prenotazione> carPrenotazioni) {
		this.carPrenotazioni = carPrenotazioni;
	}

	/**
	 * Setter Targa del veicolo
	 * @param targa
	 */
	public void setTarga(String targa) {
		this.targa = targa;
	}

	/**
	 * Setter per la proprietà documentazioni.
	 *
	 * @param documentazioni documentazioni del veicolo da settare
	 */
	public void setUtenti(Set<User> carUsers) {
		this.carUsers = carUsers;
	}
}