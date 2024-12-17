package com.devnot.CampistsIbod.infra.security.repository;

import com.devnot.CampistsIbod.infra.security.dto.UserDTO;
import com.devnot.CampistsIbod.infra.security.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    @Query("""
             SELECT new UserDTO(user.id, user.username) from UserModel user
            """)
    List<UserDTO> findUsers();

    Optional<UserModel> findByUsername(String username);
}
