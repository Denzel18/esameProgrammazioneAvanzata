package it.univpm.advancedcode.cri.services;

import java.util.List;

import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.File;

public interface FileService {

	File create(String description, Documentazione documento, String name, boolean noDownloadable);
	
	void delete(File file);
	
	List<File> getAll();
	
	File getById(long id);

	File getByName(String name);

	List<File> getByNoDownloadable(boolean noDownloadable);
	
	List<File> getFileByPost(Documentazione documento);
	
	File update(File file);
}
