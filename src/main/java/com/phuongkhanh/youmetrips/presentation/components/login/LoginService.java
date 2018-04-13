package com.phuongkhanh.youmetrips.presentation.components.login;

import com.phuongkhanh.youmetrips.presentation.models.User;

public interface LoginService {
    User login(final String email, final String password );

    void signUp(final String emailOrPhone,
                final String password,
                final String firstName,
                final String lastName);

    User loginWithFB(final String token);
}
