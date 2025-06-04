package com.globalopencampus.stargazingapi.service;

import com.globalopencampus.stargazingapi.dto.model.InstrumentDto;
import com.globalopencampus.stargazingapi.model.Instrument;
import com.globalopencampus.stargazingapi.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository instrumentRepository;

    @Autowired
    public InstrumentServiceImpl(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public Optional<Instrument> get(Long instrumentId) {
        return instrumentRepository.findById(instrumentId);
    }

    @Override
    public Optional<Instrument> add(Instrument instrument) {
        if (instrument == null) {
            return Optional.empty();
        }
        return Optional.of(instrumentRepository.save(instrument));
    }

    @Override
    public Optional<Instrument> update(Long instrumentId, InstrumentDto dto) {
        return instrumentRepository.findById(instrumentId).map(existing -> {
            existing.setNom(dto.nom());
            existing.setType(dto.type());
            existing.setEtat(dto.etat());
            return instrumentRepository.save(existing);
        });
    }

    @Override
    public boolean delete(Long instrumentId) {
        if (instrumentRepository.existsById(instrumentId)) {
            instrumentRepository.deleteById(instrumentId);
            return true;
        }
        return false;
    }

    @Override
    public Page<Instrument> getAll(Pageable pageable) {
        return instrumentRepository.findAll(pageable);
    }
}
