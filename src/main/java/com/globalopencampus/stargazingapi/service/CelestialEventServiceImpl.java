package com.globalopencampus.stargazingapi.service;

import com.globalopencampus.stargazingapi.dto.model.CelestialEventDto;
import com.globalopencampus.stargazingapi.model.CelestialEvent;
import com.globalopencampus.stargazingapi.repository.CelestialEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
public class CelestialEventServiceImpl implements CelestialEventService {

    private final CelestialEventRepository celestialEventRepository;

    @Autowired
    public CelestialEventServiceImpl(CelestialEventRepository celestialEventRepository) {
        this.celestialEventRepository = celestialEventRepository;
    }

    @Override
    public Optional<CelestialEvent> get(Long celestialEventId) {
        return celestialEventRepository.findById(celestialEventId);
    }

    @Override
    public Optional<CelestialEvent> add(CelestialEvent celestialEvent) {
        if (celestialEvent == null) {
            return Optional.empty();
        }
        return Optional.of(celestialEventRepository.save(celestialEvent));
    }

    @Override
    public Optional<CelestialEvent> update(Long celestialEventId, CelestialEventDto dto) {
        return celestialEventRepository.findById(celestialEventId).map(existing -> {
            existing.setTitre(dto.titre());
            existing.setDescription(dto.description());
            existing.setDate(dto.date());
            return celestialEventRepository.save(existing);
        });
    }

    @Override
    public boolean delete(Long celestialEventId) {
        if (celestialEventRepository.existsById(celestialEventId)) {
            celestialEventRepository.deleteById(celestialEventId);
            return true;
        }
        return false;
    }

    @Override
    public Page<CelestialEvent> getAll(Pageable pageable) {
        return celestialEventRepository.findAll(pageable);
    }
}
