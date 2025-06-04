package com.globalopencampus.stargazingapi.service;

import com.globalopencampus.stargazingapi.dto.model.AstronomerDto;
import com.globalopencampus.stargazingapi.model.Astronomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AstronomerService {
    /**
     * To get user from DB
     * @param userId the user id to get
     * @return user found with id provided
     */
    Optional<Astronomer> get(Long userId);

    /**
     * To add new user to DB
     * @param astronomerToAdd the user to add
     * @return new user added to DB
     */
    Optional<Astronomer> add(Astronomer astronomerToAdd);

    /**
     * To update existing user in DB
     * @param astronomerToUpdate the user to update
     * @return existing user updated
     */
    Optional<Astronomer> update(Astronomer astronomerToUpdate);

    /**
     * To delete existing user from DB
     * @param astronomer the user to delete
     */
    void delete(Astronomer astronomer);

    /**
     * To get all users
     * @return all users in DB
     */
    Page<Astronomer> getAll(Pageable pageable);

    /**
     * To get user from DB via username
     * @param username the username to find
     * @return user found with username provided
     */
    Optional<Astronomer> getByUsername(String username);

    Optional<Astronomer> update(Long id, AstronomerDto dto);

}
