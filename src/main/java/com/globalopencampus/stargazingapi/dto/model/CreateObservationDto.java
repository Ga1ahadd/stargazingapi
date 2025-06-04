package com.globalopencampus.stargazingapi.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CreateObservationDto {

    private Long astronomeId;
    private Long objetId;
    private String lieu;
    private String notes;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    public Long getAstronomeId() { return astronomeId; }

    public void setAstronomeId(Long astronomeId) { this.astronomeId = astronomeId; }

    public Long getObjetId() { return objetId; }

    public void setObjetId(Long objetId) { this.objetId = objetId; }

    public String getLieu() { return lieu; }

    public void setLieu(String lieu) { this.lieu = lieu; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }
}