package com.globalopencampus.stargazingapi.repository;

import com.globalopencampus.stargazingapi.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {
}
