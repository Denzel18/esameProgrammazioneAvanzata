package it.univpm.advancedcode.cri.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.Link;

@Repository("linkDao")
public class LinkDaoDefault extends DefaultDao implements LinkDao {

	/**
	 * Metodo per creare un nuovo link
	 * @param descrizione: descrizione del link
	 * @param hide: visibilit√† del link
	 * @param documento : documento a cui il link √® associato
	 * @param link: nome del link
	 * @return link creato 
	 */
	@Override
	public Link create(String descrizione, boolean hide, Documentazione documento, String link) {
		Link newLink =new Link();
		newLink.setDescrizione(descrizione);
		newLink.setDocumento(documento);
		newLink.setLink(link);
		this.getSession().save(newLink);
		return newLink;	
	}

	/**
	 * Metodo per creare un nuovo link
	 * @param descrizione: descrizione del link
	 * @param documento : documento a cui il link √® associato
	 * @param link: nome del link
	 * @return link creato 
	 */
	@Override
	public Link create(String descrizione, Documentazione documento, String link) {
		return this.create(descrizione, false, documento, link);
	}

	/**
	 * Metodo per eliminare un link
	 * @param link: link da eliminare
	 */
	@Override
	public void delete(Link link) {
		this.getSession().delete(link);
	}
	
	/**
	 * Metodo per ritornare la lista dei link
	 * @return lista dei links
	 */
	@Override
	public List<Link> getAll() {
		return getSession().
                createQuery("from Link l", Link.class).
                getResultList();
	}

	/**
	 * Metodo per cercare un link dal suo id
	 * @param id: id del link da ricercare
	 * @return link ricercato
	 */
	@Override
	public Link getById(long id) {
		return this.getSession().find(Link.class, id);
	}

	/**
	 * Metodo per aggiornare un link
	 * @param link: link da aggiornare
	 * @return link aggiornato
	 */
	@Override
	public Link update(Link link) {
		return (Link) this.getSession().merge(link);
	}

}
