package com.globalopencampus.stargazingapi.service;

import com.globalopencampus.stargazingapi.model.Observation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ObservationService {
    /**
     * Récupérer une observation
     * @param observationId l'id de l'observation à récupérer
     * @return observation trouvée avec l'id
     */
    Optional<Observation> get(Long observationId);

    /**
     * Ajouter une nouvelle observation
     * @param observation l'observation à ajouter
     * @return nouvelle observation ajoutée
     */
    Optional<Observation> add(Observation observation);

    /**
     * Mettre à jour une observation existante
     * @param observationId l'id de l'observation à mettre à jour
     * @return observation mise à jour
     */
    Optional<Observation> update(Long observationId);

    /**
     * Supprimer une observation
     * @param observationId l'id de l'observation à supprimer
     */
    boolean delete(Long observationId);;

    /**
     * Récupérer toutes les observations
     * @return toutes les observations
     */
    Page<Observation> getAll(Pageable pageable);

}
