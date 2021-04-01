package it.univpm.advancedcode.cri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.advancedcode.cri.model.dao.ManutenzioneDao;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Manutenzione;

@Service("manutenzioneService")
public class ManutenzioneServiceDefault implements ManutenzioneService {

    private ManutenzioneDao manutenzioneRepository;
    
    /**
     * Funzione per creare un nuovo manutenzione.
     *
     * @param id            
     * @param tipoManutenzione      
     * @param costoManutenzione  
     * @param car 
     * @return nuovo manutenzione creata
     */
    @Transactional
    @Override
    public Manutenzione create(long id, String tipoManutenzione, float costoManutenzione, Car car) {
    	return this.manutenzioneRepository.create(id, tipoManutenzione, costoManutenzione, car);
    }


    /**
     * Funzione per eliminare il post specificato.
     *
     * @param post post da eliminare
     */
    @Transactional
    @Override
    public void delete(Manutenzione manutenzione) {
        this.manutenzioneRepository.delete(manutenzione);
    }

    /**
     * Funzione per restituire la lista di tutte le manutenzioni 
     *
     * @return lista contenente tutti i documenti
     */
    @Transactional(readOnly = true)
    @Override
    public List<Manutenzione> getAll() {
        return this.manutenzioneRepository.getAll();
    }

    /**
     * Funzione per cercare un particolare manutenzione specificandone l'ID.
     * @param id id del manutenzione da cercare
     * @return manutenzione
     */
    @Transactional(readOnly = true)
    @Override
    public Manutenzione getById(long id) {
        return this.manutenzioneRepository.getById(id);
    }

    /**
     * Setter per la proprietà che si riferisce al DAO dell'entità manutenzione.
     * @param manutenzioneRepository DAO dell'entità Manutenzione da settare
     */
    @Autowired
    public void setManutenzioneRepository(ManutenzioneDao manutenzioneRepository) {
        this.manutenzioneRepository = manutenzioneRepository;
    }

    /**
     * Funzione per aggiornare il manutenzione
     * @param manutenzione
     * @return manutenzione aggiornato
     */
    @Transactional
    @Override
    public Manutenzione update(Manutenzione manutenzione) {
        return this.manutenzioneRepository.update(manutenzione);
    }
}
