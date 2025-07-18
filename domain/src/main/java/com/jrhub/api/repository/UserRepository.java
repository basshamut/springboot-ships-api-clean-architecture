package com.jrhub.api.domain.repository;

import com.jrhub.api.domain.model.User;

public interface UserRepository {
    User findByEmail(String username);
}
