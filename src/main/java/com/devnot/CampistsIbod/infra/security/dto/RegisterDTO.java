package com.devnot.CampistsIbod.infra.security.dto;

public record RegisterDTO(
        String username,
        String password,
        String confirmPassword
) {
}
