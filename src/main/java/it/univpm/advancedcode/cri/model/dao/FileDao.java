package it.univpm.advancedcode.cri.model.dao;

import java.util.List;

import org.hibernate.Session;

import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.File;

public interface FileDao {
	File create(String description, Documentazione documento, String name, boolean noDownloadable);
	
	void delete(File file);
	
	List<File> getAll();
	
	File getById(long id);
	
	File getByName(String name);
	
	List<File> getByNoDownloadable(boolean noDownloadable);
	
	Session getSession();

	void setSession(Session session);
	
	File update(File file);
	
}
