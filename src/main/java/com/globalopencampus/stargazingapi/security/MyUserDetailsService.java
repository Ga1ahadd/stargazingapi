package com.globalopencampus.stargazingapi.security;

import com.globalopencampus.stargazingapi.model.Astronomer;
import com.globalopencampus.stargazingapi.service.AstronomerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    AstronomerService astronomerService;

    public MyUserDetailsService(AstronomerService astronomerService){
        this.astronomerService = astronomerService;
    }

    // -- To link the user in our DB to the spring boot user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // -- Get our user from DB
        Astronomer astronomer = this.astronomerService.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // -- Convert DB user roles to Spring boot security GrantedAuthority objects
        // -- A requirement for spring security to be able to check to Roles via annotation or in security config
        // -- The prefix ROLE_ is a convention and a mandatory
        List<GrantedAuthority> authorityList = astronomer.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        // -- Link our user to spring boot user
        return new org.springframework.security.core.userdetails.User(
                astronomer.getUsername(),
                astronomer.getPassword(),
                authorityList
        );
    }
}
