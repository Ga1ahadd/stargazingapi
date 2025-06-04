package com.globalopencampus.stargazingapi.repository;

import com.globalopencampus.stargazingapi.model.CelestialEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CelestialEventRepository extends JpaRepository<CelestialEvent, Long> {
}
