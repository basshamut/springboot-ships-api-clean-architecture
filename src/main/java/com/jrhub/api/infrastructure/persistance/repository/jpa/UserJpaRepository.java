package com.jrhub.api.infrastructure.persistance.repository.jpa;

import com.jrhub.api.infrastructure.persistance.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
