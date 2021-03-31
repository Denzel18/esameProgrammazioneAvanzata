package it.univpm.advancedcode.cri.model.dao;

import java.time.*;
import java.util.List;

import org.springframework.stereotype.Repository;
import it.univpm.advancedcode.cri.model.entities.Documentazione;

@Repository("documentazioneDao")
public class DocumentazioneDaoDefault extends DefaultDao implements DocumentazioneDao {

    /**
     * Funzione creazione documento 
     * @param title
     * @param autoreUtente
     * @param descrizione
     * @param dataScadenza
     * @param costo
     */
    @Override
    public Documentazione create(String title, String descrizione, LocalDate dataScadenza, float costo) {
        Documentazione doc = new Documentazione();
        doc.setTitolo(title);
        doc.setDescrizione(descrizione);
        doc.setDataScadenza(dataScadenza);
        doc.setCosto(costo);
        this.getSession().save(doc);
        return doc;
    }
    
    /**
     * Funzione per eliminare il documento specificato.
     * @param documento documento da eliminare
     */
    @Override
    public void delete(Documentazione documento) {
        this.getSession().delete(documento);
    }
    /**
     * Funzione per restituire la lista di tutti i documenti.
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
     * @param id ID del documento da cercare
     * @return eventuale documento trovato
     */
    @Override
    public Documentazione getById(long id) {
        return getSession().find(Documentazione.class, id);
    }
    
    
    /**
     * Funzione per cercare un particolare documento specificandone il titolo.
     * @param title titolo del documento da cercare
     * @return eventuale documento trovato
     */
    @Override
    public Documentazione getByTitle(String titolo) {
        return (Documentazione) getSession().getNamedQuery("Documentazione.getByTitolo").setParameter("titolo", titolo).
                uniqueResult();
    }

    /**
     * Funzione per aggiornare il documento specificato.
     * @param documento  da aggiornare
     * @return documento aggiornato
     */
    @Override
    public Documentazione update(Documentazione documento) {
         this.getSession().update(documento);
         Documentazione updated_documento = this.getById(documento.getId());
         return updated_documento;
         
    }
}
