package com.globalopencampus.stargazingapi.service;

import com.globalopencampus.stargazingapi.dto.model.CreateStargazingSessionDto;
import com.globalopencampus.stargazingapi.model.StargazingSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StargazingSessionService {
    /**
     * Récupérer une session d'observation
     * @param stargazingSessionId l'id de la session d'observation à récupérer
     * @return session d'observation trouvée avec l'id
     */
    Optional<StargazingSession> get(Long stargazingSessionId);

    /**
     * Ajouter une nouvelle session d'observation
     * @param stargazingSession la session d'observation à ajouter
     * @return nouvelle session d'observation ajouté
     */
    Optional<StargazingSession> add(StargazingSession stargazingSession);

    /**
     * Mettre à jour une session d'observation existante
     * @param stargazingSessionId l'id de la session d'observation à mettre à jour
     * @param stargazingSessionId la session d'observation à jour
     * @return session d'observation mise à jour
     */
    Optional<StargazingSession> update(Long stargazingSessionId, CreateStargazingSessionDto dto);

    /**
     * Supprimer une session d'observation
     * @param stargazingSessionId l'id de la session d'observation à supprimer
     */
    boolean delete(Long stargazingSessionId);;

    /**
     * Récupérer tous les sessions d'observation
     * @return tous les sessions d'observation
     */
    Page<StargazingSession> getAll(Pageable pageable);

    Optional<StargazingSession> addParticipant(Long sessionId, Long astronomerId);

}
