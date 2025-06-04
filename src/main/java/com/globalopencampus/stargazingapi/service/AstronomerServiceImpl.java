package com.globalopencampus.stargazingapi.service;

import com.globalopencampus.stargazingapi.dto.model.AstronomerDto;
import com.globalopencampus.stargazingapi.model.Astronomer;
import com.globalopencampus.stargazingapi.model.RoleEnum;
import com.globalopencampus.stargazingapi.repository.AstronomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AstronomerServiceImpl implements AstronomerService {

    final AstronomerRepository astronomerRepository;

    public AstronomerServiceImpl(AstronomerRepository astronomerRepository) {
        this.astronomerRepository = astronomerRepository;
    }

    @Override
    public Optional<Astronomer> get(Long userId) {
        return this.astronomerRepository.findById(userId);
    }

    @Override
    public Optional<Astronomer> add(Astronomer astronomerToAdd) {
        astronomerToAdd.setUpdatedDate(LocalDateTime.now());
        astronomerToAdd.setCreatedDate(LocalDateTime.now());

        return Optional.of(this.astronomerRepository.save(astronomerToAdd));
    }

    @Override
    public Optional<Astronomer> update(Astronomer astronomerToUpdate) {
        astronomerToUpdate.setUpdatedDate(LocalDateTime.now());

        return Optional.of(this.astronomerRepository.save(astronomerToUpdate));
    }

    @Override
    public void delete(Astronomer astronomer) {
        this.astronomerRepository.delete(astronomer);
    }

    @Override
    public Page<Astronomer> getAll(Pageable pageable) {
        return astronomerRepository.findAll(pageable);
    }

    @Override
    public Optional<Astronomer> getByUsername(String username) {
        return Optional.ofNullable(this.astronomerRepository.findByUsername(username));
    }

    @Override
    public Optional<Astronomer> update(Long id, AstronomerDto dto) {
        return astronomerRepository.findById(id).map(existing -> {
            existing.setUsername(dto.username());
            existing.setEmail(dto.email());
            existing.setLastName(dto.lastName());
            existing.setFirstName(dto.firstName());
            existing.setPassword(dto.password());
            existing.setRoles(dto.roles());
            return astronomerRepository.save(existing);
        });
    }
}
