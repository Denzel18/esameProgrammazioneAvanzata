package it.univpm.advancedcode.cri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.advancedcode.cri.model.dao.LinkDao;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.Link;

@Service("linkService")
public class LinkServiceDefault implements LinkService {

	private LinkDao linkRepository;
	/**
	 * Metodo per creare un link
	 * @param description: descrizione del link
	 * @param hide: visibilità del link
	 * @param documento: documento associato al link
	 * @param link: link del link da creare
	 * @return link creato
	 */
	@Override
	@Transactional
	public Link create(String description, boolean hide, Documentazione documento, String link) {
		return this.linkRepository.create(description, hide, documento, link);
	}
	
	/**
	 * Metodo per creare un link
	 * @param description: descrizione del link
	 * @param documento: documento associato al link
	 * @param link: link del link da creare
	 * @return link creato
	 */
	@Override
	@Transactional
	public Link create(String description, Documentazione documento, String link) {
		return this.linkRepository.create(description, documento, link);
	}

	/**
	 * Metodo per eliminare un link
	 * @param link: link da eliminare
	 */
	@Override
	@Transactional
	public void delete(Link link) {
		this.linkRepository.delete(link);

	}

	/**
	 * Metodo per ottenere tutti i link
	 * @return lista di tutti i link
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Link> getAll() {
		return this.linkRepository.getAll();
	}
	
	/**
	 * Metodo per ricavare il link dall'id
	 * @param id: id del link da ricavare
	 * @return link ricercato
	 */
	@Transactional(readOnly=true)
	@Override
	public Link getById(long id) {
		return this.linkRepository.getById(id);
	}

	/**
	 * Metodo per ottenere i link associati ad un certo documento
	 * @param documento: documento da ricercare
	 * @return lista dei link associati al documento
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Link> getLinkByDocumentazione(Documentazione documento) {
		List<Link> links=this.linkRepository.getAll();
		links.removeIf(link -> !link.getDocumento().equals(documento));
		return links;
	}

	/**
	 * Setter per la proprietà che si riferisce al DAO dell'entità Link.
	 *
	 * @param linkRepository DAO dell'entità Link da settare
	 */
	@Autowired
	public void setLinkRepository(LinkDao linkRepository) {
		this.linkRepository = linkRepository;
	}

	/**
	 * Metodo per aggiornare un link
	 * @param link: link da aggiornare
	 * @return link aggiornato
	 */
	@Override
	@Transactional
	public Link update(Link link) {
		return this.linkRepository.update(link);
	}
}
