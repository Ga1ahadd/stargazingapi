package com.globalopencampus.stargazingapi.controller;

import com.globalopencampus.stargazingapi.dto.model.CelestialEventDto;
import com.globalopencampus.stargazingapi.model.CelestialEvent;
import com.globalopencampus.stargazingapi.service.CelestialEventService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

@RestController
@RequestMapping("/celestialEvents")
public class CelestialEventController {

    private final CelestialEventService celestialEventService;

    @Autowired
    public CelestialEventController(CelestialEventService celestialEventService) {
        this.celestialEventService = celestialEventService;
    }

    @Operation(summary = "Récupérer la liste de tous les évènements célestes")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping
    public Page<CelestialEvent> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return celestialEventService.getAll(pageable);
    }

    @Operation(summary = "Récupérer un évènement céleste via son ID")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public Optional<CelestialEvent> getById(@PathVariable Long id) {
        return celestialEventService.get(id);
    }

    @Operation(summary = "Créer un évènement céleste")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER')")
    @PostMapping
    public Optional<CelestialEvent> create(@RequestBody CelestialEvent celestialEvent) {
        return celestialEventService.add(celestialEvent);
    }

    @Operation(summary = "Modifier un évènement céleste")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PutMapping("/{id}")
    public CelestialEvent update(@PathVariable Long id, @RequestBody CelestialEventDto dto) {
        return celestialEventService.update(id, dto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "évènement introuvable"));
    }

    @Operation(summary = "Supprimer un évènement céleste")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return celestialEventService.delete(id);
    }
}
