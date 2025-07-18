package com.jrhub.api.persistance.repository;

import com.jrhub.api.domain.exception.ServiceException;
import com.jrhub.api.domain.model.User;
import com.jrhub.api.domain.repository.UserRepository;
import com.jrhub.api.persistance.entities.UserEntity;
import com.jrhub.api.persistance.repository.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserSQLRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User findByEmail(String email) {
        var user = userJpaRepository.findByEmail(email).orElseThrow(() ->new ServiceException("User not found with email: " + email, 404));

        return convertToModel(user);
    }

    private User convertToModel(UserEntity userEntity) {
        return new User(
            userEntity.getId().longValue(),
            userEntity.getEmail(),
            userEntity.getPassword(),
            userEntity.getRole()
        );
    }
}
