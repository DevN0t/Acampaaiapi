package com.devnot.CampistsIbod.infra.security.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
public record UserDTO(
    @Id
    Integer id,
    String username
) {
}
