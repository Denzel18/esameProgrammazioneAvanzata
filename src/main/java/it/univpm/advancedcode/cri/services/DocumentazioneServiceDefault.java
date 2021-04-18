package it.univpm.advancedcode.cri.services;

import java.time.LocalDate;
import java.util.List;

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

    private DocumentazioneDao documentazioneRepository;
    
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
    public Documentazione create(long id, String titolo, User autoreUtente, String descrizione, LocalDate dataScadenza, float costo,
    	    Allegato allegato, Car car) {
    	return this.documentazioneRepository.create(id, titolo, autoreUtente, descrizione, dataScadenza, costo, allegato, car);
    			
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
    public Documentazione create(long id, String titolo, User utente, String descrizione, LocalDate dataScadenza, float costo, Car car) {
    	return this.documentazioneRepository.create(id, titolo, utente, descrizione, dataScadenza, costo, car);
    }



    /**
     * Funzione per eliminare il documento specificato.
     * @param documento documento da eliminare
     */
    @Transactional
    @Override
    public void delete(Documentazione documento) {
        this.documentazioneRepository.delete(documento);
    }


    /**
     * Funzione per restituire la lista di tutti i documenti.
     *
     * @return lista contenente tutti i documenti
     */
    @Transactional(readOnly = true)
    @Override
    public List<Documentazione> getAll() {
        try{
            return this.documentazioneRepository.getAll();
        }catch(Exception e){
            System.out.println("EXCEPTION GET ALL ");
            return null; 
        }

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
        return this.documentazioneRepository.getById(id);
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
        return this.documentazioneRepository.getByTitle(title);
    }

    /**
     * Setter per la proprietà che si riferisce al DAO dell'entità Documento.
     *
     * @param documentazioneRepository DAO dell'entità Documento da settare
     */
    @Autowired
    public void setDocumentazioneRepository(DocumentazioneDao documentazioneRepository) {
        this.documentazioneRepository = documentazioneRepository;
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
        return this.documentazioneRepository.update(documento);
    }





}
