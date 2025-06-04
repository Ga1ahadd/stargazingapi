package com.globalopencampus.stargazingapi.dto.model;

public record AuthenticationRequestDto(
        String username,
        String password
){}