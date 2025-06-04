package com.globalopencampus.stargazingapi.repository;

import com.globalopencampus.stargazingapi.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
}
