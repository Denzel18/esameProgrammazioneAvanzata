package it.univpm.advancedcode.cri.model.dao;
import java.util.List;
import org.hibernate.Session;
import it.univpm.advancedcode.cri.model.entities.User;

public interface UserDao {

	User create(String username, String password, String firstName, String lastName, String ruolo);
	

	void delete(User user);

	List<User> findAll();
	
    User findUserByUsername(String username);
	
	Session getSession();
	
	void setSession(Session session);
	
	void update(User user);

}
