package it.univpm.advancedcode.cri.services;

import java.time.*;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.advancedcode.cri.model.dao.DocumentazioneDao;
import it.univpm.advancedcode.cri.model.entities.Allegato;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.User;

@Service("documentazioneService")
public class DocumentazioneServiceDefault implements DocumentazioneService {

    private DocumentazioneDao docRepository;
    
    /**
     * Funzione per creare un nuovo documento.
     *
     * @param title            
     * @param autoreUtente     
     * @param descrizione 
     * @param dataScadenza 
     * @param costo  
     * @param allegati 
     * @car car 
     * @return nuovo documento creato
     */
    @Transactional
    @Override
    public Documentazione create(String title, User utente, String descrizione, LocalDate dataScadenza, float costo, Car car) {
    	return this.docRepository.create(title, utente, descrizione, dataScadenza, costo, car);
    }

   
    /**
     * Funzione per creare un nuovo documento.
     *
     * @param title            
     * @param autoreUtente     
     * @param descrizione 
     * @param dataScadenza 
     * @param costo  
     * @param allegati 
     * @car car 
     * @return nuovo documento creato
     */
    @Transactional
    @Override
    public Documentazione create(String title, User autoreUtente, String descrizione, LocalDate dataScadenza, float costo,
    	    Allegato allegato, Car car) {
    	return this.docRepository.create(title, autoreUtente, descrizione, dataScadenza, costo, allegato, car);
    			
    }

    /**
     * Funzione per eliminare il post specificato.
     *
     * @param post post da eliminare
     */
    @Transactional
    @Override
    public void delete(Documentazione documento) {
        this.docRepository.delete(documento);
    }


    /**
     * Funzione per restituire la lista di tutti i documenti.
     *
     * @return lista contenente tutti i documenti
     */
    @Transactional(readOnly = true)
    @Override
    public List<Documentazione> getAll() {
        return this.docRepository.getAll();
    }
    
    /**
     * Funzione per cercare un particolare documento specificandone l'ID.
     *
     * @param id id del documento da cercare
     * @return documento
     */
    @Transactional(readOnly = true)
    @Override
    public Documentazione getById(long id) {
        return this.docRepository.getById(id);
    }
    
    /**
     * Funzione per cercare un particolare documento specificandone il titolo.
     *
     * @param title 
     * @return documento
     */
    @Transactional(readOnly = true)
    @Override
    public Documentazione getByTitle(String title) {
        return this.docRepository.getByTitle(title);
    }

    /**
     * Setter per la proprietà che si riferisce al DAO dell'entità Post.
     *
     * @param postRepository DAO dell'entità Post da settare
     */
    @Autowired
    public void setDocRepository(DocumentazioneDao docRepository) {
        this.docRepository = docRepository;
    }

    /**
     * Funzione per aggiornare il documento
     *
     * @param documento
     * @return documento aggiornato
     */
    @Transactional
    @Override
    public Documentazione update(Documentazione documento) {
        return this.docRepository.update(documento);
    }



}
