package com.devnot.CampistsIbod.infra.security.service;

import com.devnot.CampistsIbod.infra.security.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final JwtService jwtService;

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication){
        return jwtService.generateToken(authentication);
    }

}
