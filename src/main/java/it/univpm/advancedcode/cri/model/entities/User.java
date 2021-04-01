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
	
	private Set<Documentazione> documentazioni= new HashSet<Documentazione>(); 
	
	private Set<Prenotazione> prenotazioni = new HashSet<Prenotazione>();

//------ PRENOTAZIONI--------
	/**
	 * Getter prenotazioni
	 * @return the prenotazioni
	 */
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	public Set<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	/**
	 * Setter Prenotazioni
	 * @param prenotazioni the prenotazioni to set
	 */
	public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	//------- DOCUMENTAZIONE -----   
	/**
	 * Getter Documentazione Sottoscritta dall'utente
	 * @return the documentazioni
	 */
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	public Set<Documentazione> getDocumentazioni() {
		return documentazioni;
	}

	/**
	 * Setter documentazioni
	 * @param documentazioni the documentazioni to set
	 */
	public void setDocumentazioni(Set<Documentazione> documentazioni) {
		this.documentazioni = documentazioni;
	}

	//------------- VEICOLI ----------
	
	/** 
	 * Getter veicolo
	 * @return veicoli
	 */	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="utenti")
	public Set<Car> getCarUsers(){
		return this.carUsers;
	}

	/**
	 * Setter cars gestite dall'utente
	 * @param cars
	 */
	public void setCarUsers(Set<Car> cars) {
		this.carUsers=cars;
	}


	
	/** 
	 * Getter per la proprietà firstname dell'User
	 * @return il nome dell'utente 
	 */

	@Column(name = "firstname" , nullable = false, length = 100)
	public String getFirstname() {
		return this.firstname;
	}

	/** 
	 * Getter per la proprietà lastname dell'User 
	 * 
	 * @return cognome dell'utente 
	 */


	@Column(name = "lastname" , nullable = false, length = 100)
	public String getLastname() {
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
	@Column(name = "ruolo")
	public String getRuolo() {
		return this.ruolo;
	}
	
	/** 
	 * Setter per la proprietà firstname dell'User 
	 * @param firstname: nome dell'utente da settare
	 */

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/** 
	 * Setter per la proprietà imageProfile dell'User 
	 * @param imgprofile: nome del file contenente l'immagine profilo dell'utente da settare
	 */

	public void setImageProfile(String imgprofile) {
		this.imageProfile = imgprofile;
	}
	
	/** 
	 * Setter per la proprietà lastname dell'User 
	 * @param lastname: cognome dell'utente da settare
	 */

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	/** 
	 * Setter per la proprietà firstname dell'User 
	 * @param password: password dell'utente da settare
	 */

	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Setter della proprietà admin.
	 * @param admin specifico se l'utente è un admin o meno
	 */
	public void setRuolo(String ruolo) {
		if((ruolo == "admin")|| (ruolo == "accounting") || (ruolo == "driver"))
			this.ruolo = ruolo;
	}
	/** 
	 * Setter per la proprietà username dell'User 
	 * @param username: username da settare per l'utente 
	 */

	public void setUsername(String username) {
		this.username = username;
	}
}
