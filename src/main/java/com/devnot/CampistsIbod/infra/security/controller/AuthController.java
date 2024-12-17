package com.devnot.CampistsIbod.infra.security.controller;

import com.devnot.CampistsIbod.infra.security.dto.RegisterDTO;
import com.devnot.CampistsIbod.infra.security.dto.UserDTO;
import com.devnot.CampistsIbod.infra.security.service.AuthService;
import com.devnot.CampistsIbod.infra.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping("/auth")
    public String auth(Authentication authentication){
        return authService.authenticate(authentication);
    }

//    @PostMapping("/register")
//    public void register(@RequestBody RegisterDTO data){
//        userService.registerUser(data);
//    }
}
