package com.globalopencampus.stargazingapi.dto.model;

import java.util.Set;

public record AstronomerDto(
        String username,
        String email,
        String firstName,
        String lastName,
        String password,
        Set<String> roles
){}