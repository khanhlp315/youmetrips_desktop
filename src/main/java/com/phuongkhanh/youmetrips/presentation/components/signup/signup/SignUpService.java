package com.phuongkhanh.youmetrips.presentation.components.signup.signup;

import com.phuongkhanh.youmetrips.presentation.models.User;

/*
 * @author by LeVoGiaKhang
 */
public interface SignUpService {
    void signUp(final String emailOrPhone,
                final String password,
                final String firstName,
                final String lastName);
}
