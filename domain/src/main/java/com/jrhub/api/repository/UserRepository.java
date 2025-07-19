package com.jrhub.api.repository;

import com.jrhub.api.model.User;

public interface UserRepository {
    User findByEmail(String username);
}
