package com.phuongkhanh.youmetrips.presentation.components.signup.signup;

import com.phuongkhanh.youmetrips.presentation.models.User;
import com.phuongkhanh.youmetrips.services.api.models.SignUp;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;

/*
 * @author by LeVoGiaKhang
 */
public interface SignUpService {
    SignUp signUp(final String emailOrPhone,
                  final String password,
                  final String firstName,
                  final String lastName);

    AuthenticationStore getAuthenticationStore();
}
