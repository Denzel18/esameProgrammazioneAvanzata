package it.univpm.advancedcode.cri.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import java.time.*; 


@Entity
@Table(name = "prenotazioni")
@NamedQueries({
    @NamedQuery(
            name = "Prenotazione.getById",
            query = "FROM Prenotazione WHERE Prenotazione.PRENOTAZIONE_ID = :ID"
    )
})
public class Prenotazione implements Serializable {
	

	private long id;
    private LocalDate dataInzio; 
    private LocalDate dataFine; 
    private LocalTime oraInzio; 
    private LocalTime oraFine; 
    private String descrizione;
    
    
    private User utente;

    private Car veicolo; 
    

    /**
     * Getter veicolo che ha eseguito la prentoazione
	 * @return the veicolo
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "prenotazioneVeicolo")
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
    
	
    /**
     * Getter utente che ha eseguito la prenotazione
	 * @return the User
	 */
    @OneToOne(cascade = CascadeType.ALL)
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
	public LocalDate getDataInzio() {
		return dataInzio;
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
	public LocalTime getOraInzio() {
		return oraInzio;
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
	public void setDataInzio(LocalDate dataInzio) {
		this.dataInzio = dataInzio;
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
	public void setOraInzio(LocalTime oraInzio) {
		this.oraInzio = oraInzio;
	}
	
    
}
