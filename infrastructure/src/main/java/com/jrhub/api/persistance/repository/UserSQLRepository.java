package com.jrhub.api.persistance.repository;

import com.jrhub.api.exception.ServiceException;
import com.jrhub.api.mapper.UserEntityMapper;
import com.jrhub.api.model.User;
import com.jrhub.api.repository.UserRepository;
import com.jrhub.api.persistance.entities.UserEntity;
import com.jrhub.api.persistance.repository.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Adapter that implements the domain port using JPA
 * Now uses a dedicated mapper for entity conversion, following SRP
 */
@Repository
@RequiredArgsConstructor
public class UserSQLRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new ServiceException("User not found with email: " + email, 404));

        return UserEntityMapper.INSTANCE.toDomain(userEntity);
    }
}
