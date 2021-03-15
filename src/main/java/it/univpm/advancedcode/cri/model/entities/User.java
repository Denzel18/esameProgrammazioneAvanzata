package it.univpm.advancedcode.cri.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

	
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String imageProfile;
	private String ruolo;

	private Set<Car> carUsers = new HashSet<Car>(); 
	private Set<Documentazione> documentazioniUtente = new HashSet<Documentazione>();


	public void addCars(Car car) {
		this.carUsers.add(car);
		car.getUtenti().add(this);
	}

	/** 
	 * Metodo per aggiungere documento 
	 * 
	 * @param documento: documento da aggiugnere al Set 
	 */
	public void addDocumentazione(Documentazione documento) {
		documento.setAutoreUtente(this);
		this.documentazioniUtente.add(documento);
	}


	/** 
	 * Getter veicolo
	 *  
	 * @return veicoli
	 */	

	@ManyToMany(mappedBy="utenti")
	public Set<Car> getCars(){
		return this.carUsers;
	}


	/** 
	 * Getter per la proprietà posts dell'User
	 * @return posts dell'utente
	 */	

	@OneToMany
    @JoinColumn(name = "documentazioniUtente")
	public Set<Documentazione> getDocumentazione() {
		return this.documentazioniUtente;
	}


	/** 
	 * Getter per la proprietà firstname dell'User 
	 * 
	 * @return il nome dell'utente 
	 */

	@Column(name = "FIRSTNAME" , nullable = false, length = 100)
	public String getFirstName() {
		return this.firstname;
	}


	/** 
	 * Getter per la proprietà imageProfile dell'User 
	 * 
	 * @return stringa relativa al nome del file che contiene l'immagine profilo
	 */

	@Column(name = "IMAGE_PROFILE" , nullable = true, length = 100)
	public String getImageProfile() {
		return this.imageProfile;
	}


	/** 
	 * Getter per la proprietà lastname dell'User 
	 * 
	 * @return cognome dell'utente 
	 */


	@Column(name = "LASTNAME" , nullable = false, length = 100)
	public String getLastName() {
		return this.lastname;
	}


	/** 
	 * Getter per la proprietà password dell'User 
	 * 
	 * @return password dell'utente 
	 */

	@Column(name = "PASSWORD" , nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}


	/**
	 * Getter della proprietà admin.
	 *
	 * @return se l'utente è un admin o meno
	 */
	@Column(name = "RUOLO" , nullable=false)
	public String getRuolo() {
		return this.ruolo;
	}


	/** 
	 * Getter per la proprietà username dell'User 
	 * 
	 * @return username dell'utente 
	 */

	@Id
	@Column(name = "USERNAME", unique = true, nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}



	public void setCars(Set<Car> cars) {
		this.carUsers=cars;
	}

	/** 
	 * Setter per la proprietà posts dell'User 
	 * @param posts: post da associare all'utente 
	 */
	public void setDocumentazione(Set<Documentazione> documentazioniUtente) {
		this.documentazioniUtente = documentazioniUtente;
	}



	/** 
	 * Setter per la proprietà firstname dell'User 
	 * 
	 * @param firstname: nome dell'utente da settare
	 */

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	
	/** 
	 * Setter per la proprietà imageProfile dell'User 
	 * 
	 * @param imgprofile: nome del file contenente l'immagine profilo dell'utente da settare
	 */

	public void setImageProfile(String imgprofile) {
		this.imageProfile = imgprofile;
	}
	
	/** 
	 * Setter per la proprietà lastname dell'User 
	 * 
	 * @param lastname: cognome dell'utente da settare
	 */

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}
	
	
	/** 
	 * Setter per la proprietà firstname dell'User 
	 * 
	 * @param password: password dell'utente da settare
	 */

	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Setter della proprietà admin.
	 *
	 * @param admin specifico se l'utente è un admin o meno
	 */
	public void setRuolo(String ruolo) {
		if((ruolo == "admin")|| (ruolo == "accounting") || (ruolo == "driver"))
			this.ruolo = ruolo;
	}
	/** 
	 * Setter per la proprietà username dell'User 
	 * 
	 * @param username: username da settare per l'utente 
	 */

	public void setUsername(String username) {
		this.username = username;
	}
}
