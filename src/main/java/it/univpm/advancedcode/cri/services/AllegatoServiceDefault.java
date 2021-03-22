package it.univpm.advancedcode.cri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.advancedcode.cri.model.dao.AllegatoDao;
import it.univpm.advancedcode.cri.model.entities.Allegato;


@Service("allegatoService")
public class AllegatoServiceDefault implements AllegatoService {

	AllegatoDao allegatoRepository;
	
	/**
	 * Metodo per restituire tutti gli allegati
	 * @return lista contenente gli allegati
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Allegato> getAll() {
		return this.allegatoRepository.getAll();
	}

	/**
	 * Metodo per restituire un allegato dato un certo id
	 * @param id: id dell'allegato da cercare
	 * @return allegato ricercato
	 */
	@Transactional(readOnly = true)
	@Override
	public Allegato getById(long id) {
		return this.allegatoRepository.getById(id);
	}

	/**
	 * Metodo per aggiornare un allegato
	 * @param allegato: allegato da aggiornare
	 * @return allegato aggiornato
	 */
	@Transactional
	@Override
	public Allegato update(Allegato allegato) {
		return this.allegatoRepository.update(allegato);
	}

	/**
	 * Metodo per cancellare un allegato
	 * @param allegato: allegato da cancellare
	 */
	@Transactional
	@Override
	public void delete(Allegato allegato) {
		this.allegatoRepository.delete(allegato);
	}

	/**
	 * Setter per la proprietà che si riferisce al DAO dell'entità Attachment.
	 *
	 * @param allegatoRepository DAO dell'entità Attachment da settare
	 */
	@Autowired
	public void setLinkRepository(AllegatoDao allegatoRepository) {
		this.allegatoRepository = allegatoRepository;
	}
}
