package it.univpm.advancedcode.cri.services;

import java.util.List;
import it.univpm.advancedcode.cri.model.entities.Manutenzione;

public interface ManutenzioneService {
    Manutenzione create(long id, String tipoManutenzione, float costoManutenzione);
    void delete(Manutenzione manutenzione);
    List<Manutenzione> getAll();
    Manutenzione getById(long id);
    Manutenzione update(Manutenzione manutenzione);
}
