package it.univpm.advancedcode.cri.model.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;;


@Entity(name = "Prenotazione")
@Table(name = "prenotazioni", uniqueConstraints = {
        @UniqueConstraint(columnNames = "PRENOTAZIONE_ID")})
@NamedQueries({
    @NamedQuery(
            name = "Prenotazione.getById",
            query = "FROM Prenotazione WHERE Prenotazione.PRENOTAZIONE_ID = :ID"
    )
})
public class Prenotazione implements Serializable {
	

	private long id;
    private Date dataInzio; 
    private Date dataFine; 
    private Time oraInzio; 
    private Time oraFine; 
    private String descrizione;
    
    
    private User utente;

    private Car veicolo; 
    

    /**
     * Getter veicolo che ha eseguito la prentoazione
	 * @return the veicolo
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "prenotazione_id")
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
    @JoinColumn(name = "USERNAME")
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
	@Column(name = "DATA_FINE")
	public Date getDataFine() {
		return dataFine;
	}
	/**
	 * Getter Data di prenotazione inizio 
	 * @return dataInizio
	 */
	@Column(name = "DATA_INIZIO")
	public Date getDataInzio() {
		return dataInzio;
	}
	/**
	 * Getter Descrizione prenotazione 
	 * @return descrizione
	 */
	@Column(name = "DESCRIZIONE")
	public String getDescrizione() {
		return descrizione;
	}
	/**
     * Getter Id prenotazione
     * @return id 
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PRENOTAZIONE_ID", unique = true)
	public long getId() {
		return id;
	}
	/**
	 * Getter Ora di prenotazione (fine) 
	 * @return oraFine 
	 */
	@Column(name = "ORA_FINE")
	public Time getOraFine() {
		return oraFine;
	}
	/**
	 * Getter Ora di prenotazione (inizio) 
	 * @return oraInzio 
	 */
	@Column(name = "ORA_INIZIO")
	public Time getOraInzio() {
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
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	/**
	 * Setter Data di prenotazione (inizio)
	 * @param dataInzio
	 */
	public void setDataInzio(Date dataInzio) {
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
	public void setOraFine(Time oraFine) {
		this.oraFine = oraFine;
	}
	
	/**
	 * Setter Ora di prenozazione (inizio)
	 * @param oraInzio
	 */
	public void setOraInzio(Time oraInzio) {
		this.oraInzio = oraInzio;
	}
	
    
}
