package com.globalopencampus.stargazingapi.service;

import com.globalopencampus.stargazingapi.model.Observation;
import com.globalopencampus.stargazingapi.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class ObservationServiceImpl implements ObservationService {

    private final ObservationRepository observationRepository;

    @Autowired
    public ObservationServiceImpl(ObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }

    @Override
    public Optional<Observation> get(Long observationId) {
        return observationRepository.findById(observationId);
    }

    @Override
    public Optional<Observation> add(Observation observation) {
        if (observation == null) {
            return Optional.empty();
        }
        return Optional.of(observationRepository.save(observation));
    }

    @Override
    public Optional<Observation> update(Long observationId) {
        Optional<Observation> existing = observationRepository.findById(observationId);
        if (existing.isPresent()) {
            Observation updated = observationRepository.save(existing.get());
            return Optional.of(updated);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long observationId) {
        if (observationRepository.existsById(observationId)) {
            observationRepository.deleteById(observationId);
            return true;
        }
        return false;
    }

    @Override
    public Page<Observation> getAll(Pageable pageable) {
        return observationRepository.findAll(pageable);
    }
}