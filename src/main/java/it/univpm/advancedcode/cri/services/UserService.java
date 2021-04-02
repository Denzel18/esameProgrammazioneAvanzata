package it.univpm.advancedcode.cri.services;

import java.util.List;

import it.univpm.advancedcode.cri.model.entities.User;

public interface UserService {
	
	User create(String username, String password, String firstName, String lastName, String ruolo);
	
    User create(String username, String password, String firstName, String lastName, String ruolo, String imgProfile);
	
	void delete(User user);
		
	List<User> findAll();
	
	User findUserByUsername(String username);
	
	void update(User user);
	
}
