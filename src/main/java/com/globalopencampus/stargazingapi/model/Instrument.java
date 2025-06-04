package com.globalopencampus.stargazingapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "instruments")
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nom;
    String type;
    String etat;

    public Instrument(){
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) { this.etat = etat; }
}
