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
	private long id;
	private String titolo;
	private String descrizione;
	private LocalDate dataScadenza; 
	private float costo; 
	private Set<Allegato> allegati = new HashSet(); 
	private Car veicolo;
	private User utente;



	//Autore UTENTE insirimento documentazione
	
	/**
	 * Getter Autore inserimento del documento
	 * @return autore 
	 */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
	public User getUtente() {
		return utente;
	}
	/**
	 * Setter Autore inserimento documento
	 * @param autoreUtente
	 */
	public void setUtente(User utente) {
		this.utente = utente;
	}
	
	//ALLEGATO 

	/**
	 * Getter Allegato associato
	 * @return the allegatoDocumento
	 */
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "documento")
    public Set<Allegato> getAllegati() {
        return this.allegati;
    }

    /**
     * Setter per la proprietà attachments.
     *
     * @param attachments allegati del post da settare
     */
    public void setAllegati(Set<Allegato> allegati) {
        this.allegati = allegati;
    }

    /**
     * Metodo per aggiungere un allegato al documento.
     *
     * @param allegato allegato da aggiungere
     */
    public void addAllegati(Allegato allegato) {
        allegato.setDocumento(this);
        this.allegati.add(allegato);
    }
	
	//CAR -- DOCUMENTI RELATIVI AL VEICOLO 

	/**
	 * Getter car documentazione
	 * @return car 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	public Car getVeicolo(){
		return this.veicolo;
	}

	/**
	 * Setter Car
	 * @param cars
	 */
	public void setVeicolo(Car veicolo) {
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
	public long getId() {
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
	
	/**
	 * Setter id documento
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
}
