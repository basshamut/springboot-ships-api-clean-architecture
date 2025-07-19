package com.jrhub.api.usecase.user;

import com.jrhub.api.model.Auth;

public interface UserUseCase {
    Auth loadUserByUsername(String username);
} 