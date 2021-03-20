package it.univpm.advancedcode.cri.model.entities;

import java.io.Serializable;
import java.time.*; 
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "documentazioni")
@NamedQueries({
    @NamedQuery(
            name = "Documentazione.getByTitle",
            query = "FROM Documentazione WHERE Documentazione.TITOLO =: titolo"
    )
})
public class Documentazione implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String titolo;
	private String descrizione;
	private LocalDate dataScadenza; 
	private float costo; 
	
	private Allegato allegatoDocumento; 
	private Car veicolo;
	private User utente;

	//Autore UTENTE insirimento documentazione
	
	/**
	 * Getter Autore inserimento del documento
	 * @return autore 
	 */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
	public User getAutoreUtente() {
		return utente;
	}
	/**
	 * Setter Autore inserimento documento
	 * @param autoreUtente
	 */
	public void setAutoreUtente(User utente) {
		this.utente = utente;
	}
	
	//ALLEGATO 

	/**
	 * Getter Allegato associato
	 * @return the allegatoDocumento
	 */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "allegato_id", referencedColumnName = "allegato_id")
	public Allegato getAllegatoDocumento() {
		return allegatoDocumento;
	}
	/**
	 * Setter Allegato Associato
	 * @param allegatoDocumento the allegatoDocumento to set
	 */
	public void setAllegatoDocumento(Allegato allegatoDocumento) {
		this.allegatoDocumento = allegatoDocumento;
	}


	
	//CAR -- DOCUMENTI RELATIVI AL VEICOLO 

	/**
	 * Getter car documentazione
	 * @return car 
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "documentazioneVeicolo")
	public Car getCar(){
		return this.veicolo;
	}

	/**
	 * Setter Car
	 * @param cars
	 */
	public void setCar(Car veicolo) {
		this.veicolo=veicolo;
	}
	
	
	//----- GETTER DOCUMENTAZIONE


	/**
	 * Getter Costo documentazione
	 * @return costo
	 */
	@Column(name = "costo", nullable = false)
	public float getCosto() {
		return costo;
	}

	/**
	 * Getter Data Scandenza
	 * @return dataScadenza
	 */
	@Column (name = "dataScadenza", nullable = false)
	public LocalDate getDataScadenza() {
		return dataScadenza;
	}
	/**
	 * Getter Descrizione
	 * @return descrizione
	 */
	@Column(name = "descrizione", nullable = false, length = 300)
	public String getDescrizione() {
		return this.descrizione;
	}


	/**
	 * Getter ID Documento
	 * @return id del documento
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "documento_id", unique = true)
	public long getDocumento_id() {
		return id;
	}

	/**
	 * Getter Titolo 
	 * @return titolo
	 */
	@Column(name = "titolo", nullable = false, unique = true, length = 100)
	public String getTitolo() {
		return this.titolo;
	}

	//-----SETTER DOCUMENTAZIONE
	/**
	 * Setter Costo documentazione
	 * @param costo
	 */
	public void setCosto(float costo) {
		this.costo = costo;
	}

	/**
	 * Setter Data Scadenza
	 * @param dataScadenza
	 */
	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	/**
	 * Setter Descrizione
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setDocumento_id(long documento_id) {
		this.id = documento_id;
	}

	/**
	 * Setter Titolo 
	 * @param titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
}
