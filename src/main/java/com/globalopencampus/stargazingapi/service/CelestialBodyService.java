package com.globalopencampus.stargazingapi.service;

import com.globalopencampus.stargazingapi.dto.model.CelestialBodyDto;
import com.globalopencampus.stargazingapi.model.CelestialBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CelestialBodyService {
    /**
     * Récupérer un corps céleste
     * @param celestialBodyId l'id du corps céleste à récupérer
     * @return corps céleste trouvé avec l'id
     */
    Optional<CelestialBody> get(Long celestialBodyId);

    /**
     * Ajouter un nouveau corps céleste
     * @param celestialBody le corps céleste à ajouter
     * @return nouveau corps céleste ajouté
     */
    Optional<CelestialBody> add(CelestialBody celestialBody);

    /**
     * Mettre à jour un évènement céleste existant
     * @param celestialBodyId l'id de le corps céleste à mettre à jour
     * @param dto le corps céleste à jour
     * @return évènement céleste mis à jour
     */
    Optional<CelestialBody> update(Long celestialBodyId, CelestialBodyDto dto);

    /**
     * Supprimer un corps céleste
     * @param celestialBodyId l'id du corps céleste à supprimer
     */
    boolean delete(Long celestialBodyId);;

    /**
     * Récupérer tous les corps célestes
     * @return tous les corps célestes
     */
    Page<CelestialBody> getAll(Pageable pageable);

}
