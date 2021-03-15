package it.univpm.advancedcode.cri.services;

import java.util.List;
import java.util.Set;

import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Manutenzione;

public interface ManutenzioneService {
    Manutenzione create(long id, String tipoManutenzione, float costoManutenzione, Set<Car> cars);

    void delete(Manutenzione manutenzione);

    List<Manutenzione> getAll();

    Manutenzione getById(long id);

    Manutenzione update(Manutenzione manutenzione);

}
