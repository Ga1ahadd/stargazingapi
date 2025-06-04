package com.globalopencampus.stargazingapi.repository;

import com.globalopencampus.stargazingapi.model.Astronomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AstronomerRepository extends JpaRepository<Astronomer, Long> {
    Astronomer findByUsername(String username);
}
