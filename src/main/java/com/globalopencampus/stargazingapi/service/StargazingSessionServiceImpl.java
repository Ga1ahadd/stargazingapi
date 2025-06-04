package com.globalopencampus.stargazingapi.service;

import com.globalopencampus.stargazingapi.dto.model.CreateStargazingSessionDto;
import com.globalopencampus.stargazingapi.model.Astronomer;
import com.globalopencampus.stargazingapi.model.Instrument;
import com.globalopencampus.stargazingapi.model.StargazingSession;
import com.globalopencampus.stargazingapi.repository.AstronomerRepository;
import com.globalopencampus.stargazingapi.repository.InstrumentRepository;
import com.globalopencampus.stargazingapi.repository.StargazingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class StargazingSessionServiceImpl implements StargazingSessionService {

    private final StargazingSessionRepository stargazingSessionRepository;
    private final AstronomerRepository astronomerRepository;
    private final InstrumentRepository instrumentRepository;

    @Autowired
    public StargazingSessionServiceImpl(StargazingSessionRepository stargazingSessionRepository,
                                        AstronomerRepository astronomerRepository,
                                        InstrumentRepository instrumentRepository) {
        this.stargazingSessionRepository = stargazingSessionRepository;
        this.astronomerRepository = astronomerRepository;
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public Optional<StargazingSession> get(Long stargazingSessionId) {
        return stargazingSessionRepository.findById(stargazingSessionId);
    }

    @Override
    public Optional<StargazingSession> add(StargazingSession stargazingSession) {
        if (stargazingSession == null) {
            return Optional.empty();
        }
        return Optional.of(stargazingSessionRepository.save(stargazingSession));
    }

    @Override
    public Optional<StargazingSession> update(Long id, CreateStargazingSessionDto dto) {
        Optional<StargazingSession> sessionOpt = stargazingSessionRepository.findById(id);
        Optional<Instrument> instrumentOpt = instrumentRepository.findById(dto.getInstrumentId());

        if (sessionOpt.isPresent() && instrumentOpt.isPresent()) {
            StargazingSession session = sessionOpt.get();
            session.setDate(dto.getDate());
            session.setMeteo(dto.getMeteo());
            session.setNotes(dto.getNotes());
            session.setInstrument(instrumentOpt.get());

            return Optional.of(stargazingSessionRepository.save(session));
        }

        return Optional.empty();
    }

    @Override
    public boolean delete(Long stargazingSessionId) {
        if (stargazingSessionRepository.existsById(stargazingSessionId)) {
            stargazingSessionRepository.deleteById(stargazingSessionId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<StargazingSession> addParticipant(Long sessionId, Long astronomerId) {
        Optional<StargazingSession> sessionOpt = stargazingSessionRepository.findById(sessionId);
        Optional<Astronomer> astronomerOpt = astronomerRepository.findById(astronomerId);

        if (sessionOpt.isPresent() && astronomerOpt.isPresent()) {
            StargazingSession session = sessionOpt.get();
            session.getParticipants().add(astronomerOpt.get());
            return Optional.of(stargazingSessionRepository.save(session));
        }

        return Optional.empty();
    }

    @Override
    public Page<StargazingSession> getAll(Pageable pageable) {
        return stargazingSessionRepository.findAll(pageable);
    }
}
