package com.globalopencampus.stargazingapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "stargazingsessions")
public class StargazingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;
    private String meteo;
    private String notes;

    @ManyToMany
    @JoinTable(
            name = "session_participants",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Astronomer> participants;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;

    public StargazingSession() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public String getMeteo() { return meteo; }

    public void setMeteo(String meteo) { this.meteo = meteo; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public List<Astronomer> getParticipants() { return participants; }

    public void setParticipants(List<Astronomer> participants) { this.participants = participants; }

    public Instrument getInstrument() { return instrument; }

    public void setInstrument(Instrument instrument) { this.instrument = instrument; }
}
