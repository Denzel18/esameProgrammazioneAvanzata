package it.univpm.advancedcode.cri.services;

import java.util.List;
import java.util.Set;

import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Manutenzione;

public interface ManutenzioneService {
    Manutenzione create(long id, String tipoManutenzione, float costoManutenzione, Car car);

    void delete(Manutenzione manutenzione);

    List<Manutenzione> getAll();

    Manutenzione getById(long id);

    Manutenzione update(Manutenzione manutenzione);

}
