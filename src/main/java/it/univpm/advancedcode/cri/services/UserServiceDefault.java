package it.univpm.advancedcode.cri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.advancedcode.cri.model.dao.UserDao;
import it.univpm.advancedcode.cri.model.entities.User;

@Service("userService")
public class UserServiceDefault implements UserService, UserDetailsService {

	private UserDao userRepository;
	
	/**
	 * Metodo per creare un nuovo utente
	 * @param username username dell'utente da creare
	 * @param password password dell'utente da creare
	 * @param firstName nome dell'utente da creare
	 * @param lastName cognome dell'utente da creare
	 * @return utente creato
	 */
	@Transactional
	@Override
	public User create(String username, String password, String firstName, String lastName, String ruolo) {
		return this.userRepository.create(username, password, firstName, lastName, ruolo);
	}
	
	/**
	 * Metodo per creare un utente fornendo anche il nome dell'immagine profilo
	 * @param username username dell'utente da creare
	 * @param password password dell'utente da creare
	 * @param firstName nome dell'utente da creare
	 * @param lastName cognome dell'utente da creare
	 * @param imgProfile nome del file contente l'immagine profilo dell'utente da creare
	 * @return utente creato
	 */
	@Transactional
	@Override
	public User create(String username, String password, String firstName, String lastName, String ruolo, String imgProfile) {
		User new_user = this.userRepository.create(username, password, firstName, lastName, ruolo);
		new_user.setImageProfile(imgProfile);
		return new_user;
	}
	
	/**
	 * Metodo per eliminare un utente
	 * @param user utente da eliminare
	 */	
	@Transactional
	@Override
	public void delete(User user) {
		this.userRepository.delete(user);
	}
	
	/**
	 * Metodo per trovare tutti gli utenti registrati
	 * @return lista degli utenti registrati
	 */
	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}
	
	/**
	 * Metodo per trovare un utente dato il suo username
	 * @param username nome dell'utente da ricercare
	 * @return  utente corrispondente al nome passato
	 */
	
	@Transactional(readOnly = true)
	@Override
	public User findUserByUsername (String username) {
		return this.userRepository.findUserByUsername(username);
	}

	/**
	 * Metodo Load User By Username
	 */
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		org.springframework.security.core.userdetails.User.UserBuilder builder; 
		if (user != null) {
			String role ="";
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(user.getPassword());
			if (user.getRuolo().equals("account")) {
				role = "account";
			}
			if(user.getRuolo().equals("driver")){
				role = "driver";
			}
			if(user.getRuolo().equals("admin")) {
				role = "admin";
			}
			builder.roles(role);
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
		return builder.build();
	}
	
	
	/**
	 * Setter per la dipendenza verso l'UserDao
	 * @param userDao bean userDao
	 */
	@Autowired
	public void setUserRepository(UserDao userDao) {
		this.userRepository = userDao;
	}

	/**
	 * Metodo per aggiornare un utente 
	 * 
	 * @param user utente da modificare
	 * 
	 */
	@Transactional
	@Override
	public void update(User user) {
		this.userRepository.update(user);
	}
}
