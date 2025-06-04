package com.globalopencampus.stargazingapi.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globalopencampus.stargazingapi.model.CelestialBody;
import com.globalopencampus.stargazingapi.model.Astronomer;

import java.time.LocalDateTime;

public record ObservationDto(
        Astronomer astronome,
        CelestialBody objet,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime date,
        String lieu,
        String notes
){}