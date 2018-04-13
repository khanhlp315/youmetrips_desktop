package com.phuongkhanh.youmetrips.presentation.components.login;

import com.phuongkhanh.youmetrips.presentation.models.User;

public interface LoginService {
    User login(final String email, final String password );

}
