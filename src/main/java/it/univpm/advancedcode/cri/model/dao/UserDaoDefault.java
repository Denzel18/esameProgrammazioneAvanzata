package it.univpm.advancedcode.cri.model.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import it.univpm.advancedcode.cri.model.entities.User;

@Repository("userDao")
public class UserDaoDefault extends DefaultDao implements UserDao {

	@Autowired
    private PasswordEncoder passwordEncoder;

    /**
	 * Metodo per trovare tutti gli utenti registrati
	 * @return lista di utenti registrati
	 */
    @Override
    public List<User> findAll() {
        return getSession().
                createQuery("from User u", User.class).
                getResultList();
    }

    /**
     * Metodo per trovare un utente conoscendo il suo username
     * @param username username dell'utente da ricercare
     * @return utente corrispondente all'username specificato
     */
    @Override
    public User findUserByUsername(String username){
        return this.getSession().find(User.class, username);
    }

    /**
     * Metodo per creare un nuovo utente
     * @param username username dell'utente da creare
	 * @param password password dell'utente da creare
	 * @param firstName nome dell'utente da creare
	 * @param lastName cognome dell'utente da creare
	 * 
     */
    @Override
    public User create(String username, String password, String firstName, String lastName, String ruolo) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFirstname(firstName);
        newUser.setLastname(lastName);
        newUser.setRuolo(ruolo);
        this.getSession().save(newUser);
        return newUser;
    }

    /**
     * Metodo per aggiornare un utente 
     * @param user utente da modificare
     */
    @Override
    public void update(User user) {
        this.getSession().update(user);
    }

    /**
     * Metodo per rimuovere un utente
     * @param user da rimuovere
     */
    @Override
    public void delete(User user) {
        this.getSession().delete(user);
    }

}
