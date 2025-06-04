package com.globalopencampus.stargazingapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "observations")
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "astronome_id")
    Astronomer astronome;
    @ManyToOne
    @JoinColumn(name = "objet_id")
    CelestialBody objet;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime date;
    String lieu;
    String notes;

    public Observation(){
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Astronomer getAstronome() {
        return astronome;
    }

    public void setAstronome(Astronomer astronome) {
        this.astronome = astronome;
    }

    public CelestialBody getObjet() {
        return objet;
    }

    public void setObjet(CelestialBody idObjet) {
        this.objet = objet;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime Date) {
        this.date = date;
    }
}
