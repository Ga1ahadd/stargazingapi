package com.globalopencampus.stargazingapi.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CreateStargazingSessionDto {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;
    private String meteo;
    private String notes;
    private Long instrumentId;

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public String getMeteo() { return meteo; }

    public void setMeteo(String meteo) { this.meteo = meteo; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public Long getInstrumentId() { return instrumentId; }

    public void setInstrumentId(Long instrumentId) { this.instrumentId = instrumentId; }
}