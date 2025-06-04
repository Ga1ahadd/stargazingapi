package com.globalopencampus.stargazingapi.controller;

import com.globalopencampus.stargazingapi.dto.model.AstronomerDto;
import com.globalopencampus.stargazingapi.model.Astronomer;
import com.globalopencampus.stargazingapi.service.AstronomerService;
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
@RequestMapping("/astronomers")
public class AstronomerController {

    AstronomerService astronomerService;

    public AstronomerController(AstronomerService astronomerService){
        this.astronomerService = astronomerService;
    }

    @Operation(summary = "Récupérer un astronome via son ID")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping("/{astronomerId}")
    Astronomer get(@PathVariable Long astronomerId){
        return this.astronomerService.get(astronomerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Modifier un astronome")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER')")
    @PutMapping("/{astronomerId}")
    public Astronomer update(@PathVariable Long astronomerId, @RequestBody AstronomerDto dto) {
        return astronomerService.update(astronomerId, dto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Supprimer un astronome")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping("/{astronomerId}")
    ResponseEntity delete(@PathVariable Long astronomerId){
        Astronomer existingAstronomerToDelete = this.astronomerService.get(astronomerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.astronomerService.delete(existingAstronomerToDelete);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Récupérer la liste de tous les astronomes")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER')")
    @GetMapping
    public Page<Astronomer> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return astronomerService.getAll(pageable);
    }
}
