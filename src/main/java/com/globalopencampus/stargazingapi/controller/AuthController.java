package com.globalopencampus.stargazingapi.controller;

import com.globalopencampus.stargazingapi.model.Astronomer;
import com.globalopencampus.stargazingapi.security.JwtUtil;
import com.globalopencampus.stargazingapi.security.MyUserDetailsService;
import com.globalopencampus.stargazingapi.dto.mapper.AstronomerMapper;
import com.globalopencampus.stargazingapi.dto.model.AuthenticationRequestDto;
import com.globalopencampus.stargazingapi.dto.model.AstronomerDto;
import com.globalopencampus.stargazingapi.service.AstronomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;

    private final MyUserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final AstronomerService astronomerService;

    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, PasswordEncoder passwordEncoder, AstronomerService astronomerService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.astronomerService = astronomerService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(
            summary = "Authentification"
    )
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequestDto.username(),
                            authenticationRequestDto.password()
                    )
            );
        } catch (Exception e){
            throw new RuntimeException("Incorrect username or password", e);
        }

        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequestDto.username());
        final String jwt = this.jwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities());

        return ResponseEntity.ok(Map.of("accessToken", jwt));
    }

    @Operation(
            summary = "Créer un utilisateur"
    )
    @PostMapping("/register")
    public Astronomer register(@RequestBody AstronomerDto astronomerDto){

        // -- Check username already exists
        this.astronomerService.getByUsername(astronomerDto.username())
                .ifPresent(user -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                });


        Astronomer astronomerToAdd = AstronomerMapper.maptoUser(astronomerDto);
        astronomerToAdd.setPassword(passwordEncoder.encode(astronomerDto.password()));
        return this.astronomerService.add(astronomerToAdd)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
