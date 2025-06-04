package com.globalopencampus.stargazingapi.controller;

import com.globalopencampus.stargazingapi.dto.model.CreateObservationDto;
import com.globalopencampus.stargazingapi.model.Astronomer;
import com.globalopencampus.stargazingapi.model.CelestialBody;
import com.globalopencampus.stargazingapi.model.Observation;
import com.globalopencampus.stargazingapi.service.AstronomerService;
import com.globalopencampus.stargazingapi.service.CelestialBodyService;
import com.globalopencampus.stargazingapi.service.ObservationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/observations")
public class ObservationController {

    private final ObservationService observationService;
    private final AstronomerService astronomerService;
    private final CelestialBodyService celestialBodyService;

    public ObservationController(
            ObservationService observationService,
            AstronomerService astronomerService,
            CelestialBodyService celestialBodyService
    ) {
        this.observationService = observationService;
        this.astronomerService = astronomerService;
        this.celestialBodyService = celestialBodyService;
    }

    @Operation(summary = "Récupérer une observation via son ID")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping("/{observationId}")
    public Observation get(@PathVariable Long observationId) {
        return this.observationService.get(observationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Créer une nouvelle observation")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @PostMapping
    public Observation add(@RequestBody CreateObservationDto dto) {
        Observation observation = new Observation();

        Astronomer astronomer = astronomerService.get(dto.getAstronomeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
        CelestialBody body = celestialBodyService.get(dto.getObjetId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objet céleste introuvable"));

        observation.setAstronome(astronomer);
        observation.setObjet(body);
        observation.setLieu(dto.getLieu());
        observation.setDate(dto.getDate());
        observation.setNotes(dto.getNotes());

        return observationService.add(observation)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Operation(summary = "Mettre à jour une observation")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER')")
    @PutMapping("/{id}")
    public Observation update(@PathVariable Long id) {
        return this.observationService.update(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Operation(summary = "Supprimer une observation")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping("/{observationId}")
    public ResponseEntity<Void> delete(@PathVariable Long observationId) {
        boolean deleted = this.observationService.delete(observationId);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Récupérer toutes les observations")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER')")
    @GetMapping
    public Page<Observation> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return observationService.getAll(pageable);
    }
}
