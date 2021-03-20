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
	
	private Documentazione documentazione;
	
	private Prenotazione prenotazione; 
	
	//------ PRENOTAZIONI--------
	/**
	 * Getter prenotazioni
	 * @return prenotazioni 
	 */

	@OneToOne
	public Prenotazione getPrenotazione() {
		return this.prenotazione;
	}
	/**
	 * Setter prenotazioni
	 * @param prenotazioni 
	 */
	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}
	
	
	//------- DOCUMENTAZIONE -----   

	/**
	 * Getter Documentazione Sottoscritta dall'utente
	 * @return the documentazioneUtente
	 */
	@OneToOne
	public Documentazione getDocumentazione() {
		return this.documentazione;
	}


	/**
	 * Setter Documentazione sottoscritta dall'utente
	 * @param documentazioneUtente the documentazioneUtente to set
	 */
	public void setDocumentazione(Documentazione documentazione) {
		this.documentazione = documentazione;
	}


	//VEICOLI 
	
	/** 
	 * Getter veicolo
	 *  
	 * @return veicoli
	 */	

	@ManyToMany(fetch = FetchType.EAGER, mappedBy="utenti")
	public Set<Car> getCars(){
		return this.carUsers;
	}

	/**
	 * Setter cars gestite dall'utente
	 * @param cars
	 */
	public void setCars(Set<Car> cars) {
		this.carUsers=cars;
	}

	/** 
	 * Getter per la proprietà firstname dell'User
	 * @return il nome dell'utente 
	 */

	@Column(name = "firstname" , nullable = false, length = 100)
	public String getFirstName() {
		return this.firstname;
	}

	/** 
	 * Getter per la proprietà lastname dell'User 
	 * 
	 * @return cognome dell'utente 
	 */


	@Column(name = "lastname" , nullable = false, length = 100)
	public String getLastName() {
		return this.lastname;
	}

	/** 
	 * Getter per la proprietà imageProfile dell'User 
	 * 
	 * @return stringa relativa al nome del file che contiene l'immagine profilo
	 */

	@Column(name = "imageProfile" , nullable = true, length = 100)
	public String getImageProfile() {
		return this.imageProfile;
	}

	/** 
	 * Getter per la proprietà username dell'User 
	 * 
	 * @return username dell'utente 
	 */

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}


	/** 
	 * Getter per la proprietà password dell'User 
	 * 
	 * @return password dell'utente 
	 */

	@Column(name = "password" , nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}


	/**
	 * Getter della proprietà admin.
	 *
	 * @return se l'utente è un admin o meno
	 */
	@Column(name = "ruolo" , nullable=false)
	public String getRuolo() {
		return this.ruolo;
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
