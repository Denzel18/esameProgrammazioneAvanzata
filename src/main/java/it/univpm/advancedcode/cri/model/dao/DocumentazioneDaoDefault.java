package it.univpm.advancedcode.cri.model.dao;

import java.time.*;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import it.univpm.advancedcode.cri.model.entities.Allegato;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.User;

@Repository("documentazioneDao")
public class DocumentazioneDaoDefault extends DefaultDao implements DocumentazioneDao {

    /**
     * Funzione creazione documento 
     * @param title
     * @param autoreUtente
     * @param descrizione
     * @param dataScadenza
     * @param costo
     * @param car 
     */
    @Override
    public Documentazione create(String title, User autoreUtente, String descrizione, LocalDate dataScadenza, float costo, Car car) {
        Documentazione doc = new Documentazione();
        doc.setTitolo(title);
        doc.setDescrizione(descrizione);
        doc.setCar(car);
        doc.setDataScadenza(dataScadenza);
        doc.setCosto(costo);
        this.getSession().save(doc);
        return doc;
    }

    /**
     * Funzione creazione documento 
     * @param title
     * @param autoreUtente
     * @param descrizione
     * @param dataScadenza
     * @param costo
     * @param allegato
     * @param car 
     */
    @Override
    public Documentazione create(String title, User autoreUtente, String descrizione, LocalDate dataScadenza, float costo,
    		Allegato allegato, Car car) {

        Documentazione doc = new Documentazione();
        doc.setTitolo(title);
        doc.setDescrizione(descrizione);
        doc.setCar(car);
        doc.setAllegatoDocumento(allegato);
        doc.setDataScadenza(dataScadenza);
        doc.setCosto(costo);
        this.getSession().save(doc);
        return doc;
    }

    /**
     * Funzione per eliminare il post specificato.
     *
     * @param post post da eliminare
     */
    @Override
    public void delete(Documentazione documento) {
        this.getSession().delete(documento);
    }
    /**
     * Funzione per restituire la lista di tutti i documenti.
     *
     * @return lista contenente tutti i documenti
     */
    @Override
    public List<Documentazione> getAll() {
  	
        return getSession().
                createQuery("from Documentazione d", Documentazione.class).
                getResultList();
    }
    
    /**
     * Funzione per cercare un particolare documento specificandone l'ID.
     *
     * @param id ID del documento da cercare
     * @return eventuale documento trovato
     */
    @Override
    public Documentazione getById(long id) {
        return getSession().find(Documentazione.class, id);
    }
    
    
    /**
     * Funzione per cercare un particolare documento specificandone il titolo.
     *
     * @param title titolo del documento da cercare
     * @return eventuale documento trovato
     */
    @Override
    public Documentazione getByTitle(String title) {
        return (Documentazione) getSession().getNamedQuery("Documentazione.getByTitolo").setParameter("title", title).
                uniqueResult();
    }

    /**
     * Funzione per aggiornare il documento specificato.
     *
     * @param documento  da aggiornare
     * @return documento aggiornato
     */
    @Override
    public Documentazione update(Documentazione documento) {
         this.getSession().update(documento);
         Documentazione updated_documento = this.getById(documento.getDocumento_id());
         return updated_documento;
         
    }

}
