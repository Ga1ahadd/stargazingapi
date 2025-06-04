package com.globalopencampus.stargazingapi.controller;

import com.globalopencampus.stargazingapi.model.StargazingSuggestionDTO;
import com.globalopencampus.stargazingapi.service.MistralAiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AiController {
    MistralAiService mistralAiService;

    public AiController(MistralAiService mistralAiService){
        this.mistralAiService = mistralAiService;
    }

    @Operation(
            summary = "Suggestion d'observations célestes",
            description = "Suggestion d'observations célestes en fonction de la ville, du pays et du mois de l'année"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "L'IA a répondu",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request body") })
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @PostMapping("/stargazing-suggestions")
    String getStargazingSuggestionFromAi(@RequestBody StargazingSuggestionDTO starSuggestionDTO){
        StringBuilder prompt = new  StringBuilder();
        prompt
                .append("'Bonjour ! Je voudrais des suggestions d'observations célestes': ")
                .append("Pays: " + starSuggestionDTO.country())
                .append("Ville: " + starSuggestionDTO.city())
                .append("Mois: " + starSuggestionDTO.month());

        return mistralAiService.call(prompt.toString());
    }

    @Operation(
            summary = "Discuter avec Stella le chatbot",
            description = "Discuter avec Stella le chatbot via le service IA de Mistral"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Stella a réponsu",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid prompt") })
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @PostMapping("/stella")
    String chat(@RequestBody String prompt){
        return mistralAiService.call(prompt);
    }
}