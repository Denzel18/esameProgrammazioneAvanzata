package it.univpm.advancedcode.cri.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.File;

@Repository("fileDao")
public class FileDaoDefault extends DefaultDao implements FileDao  {

	/**
	 * Metodo per creare un nuovo file
	 * @param description: descrizione del file da creare
	 * @param hide: visibilità del file
	 * @param post: post a cui il file è associato
	 * @param name: nome del file
	 * @param noDownloadable: flag per indicare se il file è scaricabile o meno
	 * @return file creato
	 */
	@Override
	public File create(String description, Documentazione documento, String name, boolean noDownloadable) {
		File file=new File();
		file.setDescription(description);
		file.setDocumento(documento);
		file.setName("post" + documento.getDocumento_id() + "_" + name);
		file.setNoDownloadable(noDownloadable);
		this.getSession().save(file);
		return file;
	}
	
	/**
	 * Metodo per eliminare un file
	 * @param file: file da eliminare
	 */
	@Override
	public void delete(File file) {
		this.getSession().delete(file);
	}
	
	/**
	 * Metodo per ritornare tutti i file
	 * @return lista di tutti i file
	 */
	@Override
	public List<File> getAll() {
		 return getSession().
	                createQuery("from File f", File.class).
	                getResultList();
	}

	/**
	 * Metodo per ottenere un file dato un id
	 * @param id: id del file da cercare
	 * @return file ricercato
	 */
	@Override
	public File getById(long id) {
		return getSession().find(File.class, id);
	}

	/**
	 * Metodo per ottenere un file dal nome
	 * @param name: nome del file da cercare
	 * @return file con nome ricercato
	 */
	@Override
	public File getByName(String name) {
		return getSession().createNamedQuery("getFileByName", File.class).setParameter("name", name).uniqueResult();
	}
	
	/**
	 * Metodo per ricercare la lista dei file che sono scaricabili o meno
	 * @param noDownloadable: parametro per indicare se si è interessati a file scaricabili o meno
	 * @return lista dei file scaricabili o meno
	 */
	@Override
	public List<File> getByNoDownloadable(boolean noDownloadable) {
		return getSession().createNamedQuery("getByDownloadable", File.class).setParameter("noDownloadable", noDownloadable).getResultList();
	}

	/**
	 * Metodo per aggiornare un file
	 * @param file: file da aggiornare
	 * @return file aggiornato
	 */
	@Override
	public File update(File file) {
		return (File) this.getSession().merge(file);
	}

}
