package com.devnot.CampistsIbod.infra.security.service;

import com.devnot.CampistsIbod.infra.security.dto.RegisterDTO;
import com.devnot.CampistsIbod.infra.security.dto.UserDTO;
import com.devnot.CampistsIbod.infra.security.model.UserModel;
import com.devnot.CampistsIbod.infra.security.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void registerUser(RegisterDTO data) {
        UserModel user = new UserModel();
        if(!data.password().equals(data.confirmPassword())){
            throw new RuntimeException("Passwords don't match");
        }
        user.setPassword(bCryptPasswordEncoder.encode(data.password()));
        user.setUsername(data.username());
        userRepository.save(user);
    }

    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userRepository.findUsers());
    }
}
