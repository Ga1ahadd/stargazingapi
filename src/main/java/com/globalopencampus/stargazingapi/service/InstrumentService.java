package com.globalopencampus.stargazingapi.service;

import com.globalopencampus.stargazingapi.dto.model.InstrumentDto;
import com.globalopencampus.stargazingapi.model.Instrument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface InstrumentService {
    /**
     * Récupérer un instrument
     * @param instrumentId l'id de l'instrument à récupérer
     * @return instrument trouvé avec l'id
     */
    Optional<Instrument> get(Long instrumentId);

    /**
     * Ajouter un nouvel instrument
     * @param instrument l'instrument à ajouter
     * @return nouvel instrument ajouté
     */
    Optional<Instrument> add(Instrument instrument);

    /**
     * Mettre à jour un instrument existant
     * @param instrumentId l'id de l'instrument à mettre à jour
     * @param dto l'instrument à jour
     * @return instrument mis à jour
     */
    Optional<Instrument> update(Long instrumentId, InstrumentDto dto);

    /**
     * Supprimer un instrument
     * @param instrumentId l'id de l'instrument à supprimer
     */
    boolean delete(Long instrumentId);;

    /**
     * Récupérer tous les instruments
     * @return tous les instruments
     */
    Page<Instrument> getAll(Pageable pageable);

}
