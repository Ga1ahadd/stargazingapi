package com.globalopencampus.stargazingapi.repository;

import com.globalopencampus.stargazingapi.model.StargazingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StargazingSessionRepository extends JpaRepository<StargazingSession, Long> {
}
