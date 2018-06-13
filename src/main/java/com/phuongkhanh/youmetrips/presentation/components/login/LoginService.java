package com.phuongkhanh.youmetrips.presentation.components.login;

import com.phuongkhanh.youmetrips.services.api.models.Login;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;

public interface LoginService {
    Login login(final String email, final String password );

    Login loginWithFB(final String token);

    String getAccessToken();

    AuthenticationStore getAuthenticationStore();

}
