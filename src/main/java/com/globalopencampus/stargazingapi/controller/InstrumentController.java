package com.globalopencampus.stargazingapi.controller;

import com.globalopencampus.stargazingapi.dto.model.InstrumentDto;
import com.globalopencampus.stargazingapi.model.Instrument;
import com.globalopencampus.stargazingapi.service.InstrumentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instruments")
public class InstrumentController {

    private final InstrumentService instrumentService;

    @Autowired
    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @Operation(summary = "Récupérer la liste de tous les instruments")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping
    public Page<Instrument> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return instrumentService.getAll(pageable);
    }

    @Operation(summary = "Récupérer un instrument via son ID")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public Optional<Instrument> getById(@PathVariable Long id) {
        return instrumentService.get(id);
    }

    @Operation(summary = "Créer un instrument")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER')")
    @PostMapping
    public Optional<Instrument> create(@RequestBody InstrumentDto dto) {
        Instrument instrument = new Instrument();
        instrument.setNom(dto.nom());
        instrument.setType(dto.type());
        instrument.setEtat(dto.etat());

        return instrumentService.add(instrument);
    }


    @Operation(summary = "Modifier un instrument")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PutMapping("/{id}")
    public Instrument update(@PathVariable Long id, @RequestBody InstrumentDto dto) {
        return instrumentService.update(id, dto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instrument introuvable"));
    }

    @Operation(summary = "Supprimer un instrument")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return instrumentService.delete(id);
    }
}
