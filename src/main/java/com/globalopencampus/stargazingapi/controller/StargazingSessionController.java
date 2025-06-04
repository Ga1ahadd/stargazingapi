package com.globalopencampus.stargazingapi.controller;

import com.globalopencampus.stargazingapi.dto.model.CreateStargazingSessionDto;
import com.globalopencampus.stargazingapi.model.Instrument;
import com.globalopencampus.stargazingapi.model.StargazingSession;
import com.globalopencampus.stargazingapi.service.InstrumentService;
import com.globalopencampus.stargazingapi.service.StargazingSessionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/stargazingSessions")
public class StargazingSessionController {

    private final StargazingSessionService stargazingSessionService;
    private final InstrumentService instrumentService;

    @Autowired
    public StargazingSessionController(StargazingSessionService stargazingSessionService,
                                       InstrumentService instrumentService) {
        this.stargazingSessionService = stargazingSessionService;
        this.instrumentService = instrumentService;
    }

    @Operation(summary = "récupèrer toutes les sessions d'observation")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public Page<StargazingSession> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return stargazingSessionService.getAll(pageable);
    }

    @Operation(summary = "Récupèrer une session d'observation via son ID")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public Optional<StargazingSession> getById(@PathVariable Long id) {
        return stargazingSessionService.get(id);
    }

    @Operation(summary = "Créer une session d'observation")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER')")
    @PostMapping
    public Optional<StargazingSession> create(@RequestBody CreateStargazingSessionDto dto) {
        StargazingSession session = new StargazingSession();

        session.setDate(dto.getDate());
        session.setMeteo(dto.getMeteo());
        session.setNotes(dto.getNotes());

        Instrument instrument = instrumentService.get(dto.getInstrumentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instrument introuvable"));
        session.setInstrument(instrument);

        session.setParticipants(new ArrayList<>());

        return stargazingSessionService.add(session);
    }

    @Operation(summary = "Ajouter un participant à une session d'observation")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER')")
    @PutMapping("/{sessionId}/participants/{astronomerId}")
    public StargazingSession addParticipant(@PathVariable Long sessionId, @PathVariable Long astronomerId) {
        return stargazingSessionService.addParticipant(sessionId, astronomerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Modifier une session d'observation")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER')")
    @PutMapping("/{id}")
    public StargazingSession update(@PathVariable Long id, @RequestBody CreateStargazingSessionDto dto) {
        return stargazingSessionService.update(id, dto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Supprimer une session d'observation")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return stargazingSessionService.delete(id);
    }
}
