package com.jrhub.api.application.usecase;

import com.jrhub.api.domain.model.Auth;
import com.jrhub.api.domain.repository.UserRepository;
import com.jrhub.api.domain.usecase.user.UserUseCase;

import java.util.Base64;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserUseCaseImpl implements UserUseCase {

    private final UserRepository userRepository;

    public UserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Auth loadUserByUsername(String username) {
        final Set<String> authorities = new HashSet<>();

        var user = userRepository.findByEmail(username);

        if (Objects.nonNull(user)) {
            authorities.add("ROLE_" + user.getRole());
            var pass = new String(Base64.getDecoder().decode(user.getPassword()));

            return new Auth(user.getEmail(), pass, authorities);
        }

        return null;
    }

}
