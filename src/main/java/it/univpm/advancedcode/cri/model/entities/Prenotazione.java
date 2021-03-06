package it.univpm.advancedcode.cri.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.time.*; 


@Entity
@Table(name = "prenotazioni")
public class Prenotazione implements Serializable {
	

	private long id;
    private LocalDate dataInizio; 
    private LocalDate dataFine; 
    private LocalTime oraInizio; 
    private LocalTime oraFine; 
    private String descrizione;	

	private User utente;

    private Car veicolo; 
    

	//----------- VEICOLO -----------
    /**
     * Getter veicolo che ha eseguito la prentoazione
	 * @return the veicolo
	 */
    @ManyToOne
    @JoinColumn(name = "veicolo_id")
	public Car getVeicolo() {
		return this.veicolo;
	}
	/**
	 * Setter veicolo che ha eseguito la prenotazione
	 * @param veicolo
	 */
	public void setVeicolo(Car veicolo) {
		this.veicolo = veicolo;
	}
    
	//---------- UTENTE --------
    /**
     * Getter utente che ha eseguito la prenotazione
	 * @return the User
	 */
    @ManyToOne
    @JoinColumn(name = "username")
	public User getUtente() {
		return this.utente;
	}
	/**
	 * Setter utente che ha eseguito la prenotazione
	 * @param utente
	 */
	public void setUtente(User utente) {
		this.utente = utente;
	}
	
	//------ GETTER PRENOTAZIONE
	/**
	 * Getter Data di prenotazione fine 
	 * @return dataFine
	 */
	@Column(name = "dataFine")
	public LocalDate getDataFine() {
		return dataFine;
	}
	/**
	 * Getter Data di prenotazione inizio 
	 * @return dataInizio
	 */
	@Column(name = "dataInizio")
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	/**
	 * Getter Descrizione prenotazione 
	 * @return descrizione
	 */
	@Column(name = "descrizione")
	public String getDescrizione() {
		return descrizione;
	}
	/**
     * Getter Id prenotazione
     * @return id 
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prenotazione_id", unique = true)
	public long getId() {
		return id;
	}
	/**
	 * Getter Ora di prenotazione (fine) 
	 * @return oraFine 
	 */
	@Column(name = "oraFine")
	public LocalTime getOraFine() {
		return oraFine;
	}
	/**
	 * Getter Ora di prenotazione (inizio) 
	 * @return oraInzio 
	 */
	@Column(name = "oraInizio")
	public LocalTime getOraInizio() {
		return oraInizio;
	}
	/**
	 * Getter Utente prenotazione
	 * @return utente
	 */
	

	//------- SETTER PRENOTAZIONE
	/**
	 * Setter Data di prenotazione (fine)
	 * @param dataFine
	 */
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	/**
	 * Setter Data di prenotazione (inizio)
	 * @param dataInzio
	 */
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	/**
	 * Setter Descrizione prenotazione
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * Setter Id prenotazione
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Setter Ora di prenozazione (fine)
	 * @param oraFine
	 */
	public void setOraFine(LocalTime oraFine) {
		this.oraFine = oraFine;
	}
	
	/**
	 * Setter Ora di prenozazione (inizio)
	 * @param oraInzio
	 */
	public void setOraInizio(LocalTime oraInzio) {
		this.oraInizio = oraInzio;
	}

	
	/** 
	 * @return String
	 */
	public String toString(){
		String s=""; 
		s+=" "+this.getDescrizione();
		s+=" "+this.getDataFine();
		s+=" "+this.getDataInizio();
		s+=" "+this.getOraFine();
		s+=" "+this.getOraInizio();
		return s; 

	}
	
    
}
