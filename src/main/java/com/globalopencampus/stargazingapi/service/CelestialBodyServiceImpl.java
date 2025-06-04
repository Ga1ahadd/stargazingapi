package com.globalopencampus.stargazingapi.service;

import com.globalopencampus.stargazingapi.dto.model.CelestialBodyDto;
import com.globalopencampus.stargazingapi.model.CelestialBody;
import com.globalopencampus.stargazingapi.repository.CelestialBodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class CelestialBodyServiceImpl implements CelestialBodyService {

    private final CelestialBodyRepository celestialBodyRepository;

    @Autowired
    public CelestialBodyServiceImpl(CelestialBodyRepository celestialBodyRepository) {
        this.celestialBodyRepository = celestialBodyRepository;
    }

    @Override
    public Optional<CelestialBody> get(Long celestialBodyId) {
        return celestialBodyRepository.findById(celestialBodyId);
    }

    @Override
    public Optional<CelestialBody> add(CelestialBody celestialBody) {
        if (celestialBody == null) {
            return Optional.empty();
        }
        return Optional.of(celestialBodyRepository.save(celestialBody));
    }

    @Override
    public Optional<CelestialBody> update(Long celestialEventId, CelestialBodyDto dto) {
        return celestialBodyRepository.findById(celestialEventId).map(existing -> {
            existing.setNom(dto.nom());
            existing.setType(dto.type());
            return celestialBodyRepository.save(existing);
        });
    }

    @Override
    public boolean delete(Long celestialBodyId) {
        if (celestialBodyRepository.existsById(celestialBodyId)) {
            celestialBodyRepository.deleteById(celestialBodyId);
            return true;
        }
        return false;
    }

    @Override
    public Page<CelestialBody> getAll(Pageable pageable) {
        return celestialBodyRepository.findAll(pageable);
    }
}
