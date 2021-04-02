package it.univpm.advancedcode.cri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.advancedcode.cri.model.dao.FileDao;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.File;


@Service("fileService")
public class FileServiceDefault implements FileService {

	FileDao fileRepository;
	
	/**
	 * Metodo per ricavare tutti i file
	 */
	@Transactional(readOnly=true)
	@Override
	public List<File> getAll() {
		return this.fileRepository.getAll();
	}

	/**
	 * Metodo per ricavare i file da un id
	 * @param id: id del file da ricercare
	 * @return file ricercato
	 */
	@Transactional(readOnly=true)
	@Override
	public File getById(long id) {
		return this.fileRepository.getById(id);
	}

	/**
	 * Metodo per ricavare i file dal nome
	 * @param name: nome del file da ricavare
	 * @return file ricercato
	 */
	@Transactional(readOnly=true)
	@Override
	public File getByName(String name) {
		return this.fileRepository.getByName(name);
	}

	/**
	 * Metodo per ricavare la lista dei file scaribili o non scaricabili
	 * @param noDownloadable: flag per indicare se si è interessati a file scaricabili o no
	 * @return lista dei file che soddisfano il parametro
	 */
	@Transactional(readOnly=true)
	@Override
	public List<File> getByNoDownloadable(boolean noDownloadable) {
		return this.fileRepository.getByNoDownloadable(noDownloadable);
	}
	
	/**
	 * Metodo per restituire i file associati ad un certo documento
	 * @param documentazione: documentazione ricercato
	 * @return lista dei file associati al documentazione
	 */
	@Transactional(readOnly=true)
	@Override
	public List<File> getFileByPost(Documentazione documentazione) {
		List<File> files=this.fileRepository.getAll();
		files.removeIf(file -> !file.getDocumento().equals(documentazione));
		return files;
	}

	/**
	 * Metodo per creare un file
	 * @param description: descrizione del file 
	 * @param hide: visibilità del file
	 * @param post: post a cui il file è associato
	 * @param name: nome del file
	 * @param noDownloadable: flag per indicare se il file è scaricabile o meno
	 */
	@Override
	@Transactional
	public File create(String description, Documentazione documento,String name, boolean noDownloadable) {
		return this.fileRepository.create(description, documento, name, noDownloadable);
	}
	
	/**
	 * Metodo per aggiornare un file
	 * @param file: file da aggiornare
	 * @return file aggiornato	
	 */
	@Transactional
	@Override
	public File update(File file) {
		return this.fileRepository.update(file);
	}

	/**
	 * Metodo per cancellare un file
	 * @param file: file da eliminare
	 */
	@Transactional
	@Override
	public void delete(File file) {
		this.fileRepository.delete(file);
	}

	/**
	 * Setter per la proprietà che si riferisce al DAO dell'entità File.
	 * @param fileRepository DAO dell'entità File da settare
	 */
	@Autowired
	public void setFileRepository(FileDao fileRepository) {
		this.fileRepository = fileRepository;
	}
}
