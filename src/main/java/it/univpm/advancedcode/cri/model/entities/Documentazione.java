package it.univpm.advancedcode.cri.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity(name = "Documentazione")
@Table(name = "Documentazioni", uniqueConstraints = {
        @UniqueConstraint(columnNames = "DOCUMENTO_ID")})
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
	private User utente;
	private String descrizione;
	private Date dataScadenza; 
	private float costo; 
	private Set<Allegato> allegatiDocumenti = new HashSet<>();
	private Set<Car> cars = new HashSet<>();

	/**
	 * Metodo per aggiungere un allegato al documento
	 *
	 * @param allegato allegato da aggiungere
	 */
	public void addAllegati(Allegato allegato) {
		allegato.setDocumento(this);
		this.allegatiDocumenti.add(allegato);
	}

	void addCars(Car car) {
		this.cars.add(car);
		car.getDocumentazioni().add(this);
	}


	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "documento")
	public Set<Allegato> getAllegati() {
		return this.allegatiDocumenti;
	}

	/**
	 * Getter Autore inserimento del documento
	 * @return autore 
	 */

	@ManyToOne(targetEntity = it.univpm.advancedcode.cri.model.entities.User.class)
	@JoinColumn(name = "UTENTE", referencedColumnName = "USERNAME")
	public User getAutoreUtente() {
		return utente;
	}

	@ManyToMany(mappedBy="documentazioni")
	public Set<Car> getCars(){
		return this.cars;
	}
	/**
	 * Getter Costo documentazione
	 * @return costo
	 */
	@Column(name = "COSTO", nullable = false)
	public float getCosto() {
		return costo;
	}

	/**
	 * Getter Data Scandenza
	 * @return dataScadenza
	 */
	@Column (name = "DATA_SCADENZA", nullable = false)
	public Date getDataScadenza() {
		return dataScadenza;
	}
	/**
	 * Getter Descrizione
	 * @return descrizione
	 */
	@Column(name = "DESCRIZIONE", nullable = false, length = 300)
	public String getDescrizione() {
		return this.descrizione;
	}


	/**
	 * Getter ID Documento
	 * @return id del documento
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DOCUMENTO_ID", unique = true)
	public long getDocumento_id() {
		return id;
	}

	/**
	 * Getter Titolo 
	 * @return titolo
	 */
	@Column(name = "TITOLO", nullable = false, unique = true, length = 100)
	public String getTitolo() {
		return this.titolo;
	}
	/**
	 * Setter per la proprietà attachments.
	 *
	 * @param attachments allegati del post da settare
	 */
	public void setAllegati(Set<Allegato> allegatiDocumenti) {
		this.allegatiDocumenti = allegatiDocumenti;
	}
	/**
	 * Setter Autore inserimento documento
	 * @param autoreUtente
	 */
	public void setAutoreUtente(User utente) {
		this.utente = utente;
	}
	/**
	 * Setter Cars 
	 * @param cars
	 */

	public void setCars(Set<Car> cars) {
		this.cars=cars;
	}

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
	public void setDataScadenza(Date dataScadenza) {
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
