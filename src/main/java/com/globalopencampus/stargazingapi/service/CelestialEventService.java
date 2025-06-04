package com.globalopencampus.stargazingapi.service;

import com.globalopencampus.stargazingapi.dto.model.CelestialEventDto;
import com.globalopencampus.stargazingapi.model.CelestialEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CelestialEventService {
    /**
     * Récupérer un évènement céleste
     * @param celestialEventId l'id de l'évènement céleste à récupérer
     * @return évènement céleste trouvé avec l'id
     */
    Optional<CelestialEvent> get(Long celestialEventId);

    /**
     * Ajouter un nouvel évènement céleste
     * @param celestialEvent l'évènement céleste à ajouter
     * @return nouvel évènement céleste ajouté
     */
    Optional<CelestialEvent> add(CelestialEvent celestialEvent);

    /**
     * Mettre à jour un évènement céleste existant
     * @param celestialEventId l'id de l'évènement céleste à mettre à jour
     * @param dto l'évènement céleste à jour
     * @return évènement céleste mis à jour
     */
    Optional<CelestialEvent> update(Long celestialEventId, CelestialEventDto dto);

    /**
     * Supprimer un évènement céleste
     * @param celestialEventId l'id de l'évènement céleste à supprimer
     */
    boolean delete(Long celestialEventId);;

    /**
     * Récupérer tous les évènements célestes
     * @return tous les évènements célestes
     */
    Page<CelestialEvent> getAll(Pageable pageable);

}
