package com.globalopencampus.stargazingapi.controller;

import com.globalopencampus.stargazingapi.dto.model.CelestialBodyDto;
import com.globalopencampus.stargazingapi.model.CelestialBody;
import com.globalopencampus.stargazingapi.service.CelestialBodyService;
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
@RequestMapping("/celestialBodies")
public class CelestialBodyController {

    private final CelestialBodyService celestialBodyService;

    @Autowired
    public CelestialBodyController(CelestialBodyService celestialBodyService) {
        this.celestialBodyService = celestialBodyService;
    }

    @Operation(summary = "Récupérer la liste de tous les objets célestes")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping
    public Page<CelestialBody> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return celestialBodyService.getAll(pageable);
    }

    @Operation(summary = "Récupérer un objet céleste via son ID")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public Optional<CelestialBody> getById(@PathVariable Long id) {
        return celestialBodyService.get(id);
    }

    @Operation(summary = "Créer un objet céleste")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER')")
    @PostMapping
    public Optional<CelestialBody> create(@RequestBody CelestialBody celestialBody) {
        return celestialBodyService.add(celestialBody);
    }

    @Operation(summary = "Modifier un objet céleste")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PutMapping("/{id}")
    public CelestialBody update(@PathVariable Long id, @RequestBody CelestialBodyDto dto) {
        return celestialBodyService.update(id, dto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "objet introuvable"));
    }

    @Operation(summary = "Supprimer un objet céleste")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return celestialBodyService.delete(id);
    }
}
