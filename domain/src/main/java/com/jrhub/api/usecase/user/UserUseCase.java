package com.jrhub.api.domain.usecase.user;

import com.jrhub.api.domain.model.Auth;

public interface UserUseCase {
    Auth loadUserByUsername(String username);
} 