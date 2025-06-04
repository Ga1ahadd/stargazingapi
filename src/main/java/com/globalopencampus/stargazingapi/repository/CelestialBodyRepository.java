package com.globalopencampus.stargazingapi.repository;

import com.globalopencampus.stargazingapi.model.CelestialBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CelestialBodyRepository extends JpaRepository<CelestialBody, Long> {
}
