package com.phuongkhanh.youmetrips.services.components;

import com.phuongkhanh.youmetrips.presentation.components.login.LoginService;
import com.phuongkhanh.youmetrips.presentation.models.User;
import com.phuongkhanh.youmetrips.services.api.RestApi;
import com.phuongkhanh.youmetrips.services.api.exceptions.ApiCodedException;
import com.phuongkhanh.youmetrips.services.api.models.Login;

public class LoginServiceImpl implements LoginService {
    private final RestApi _api;

    public LoginServiceImpl( final RestApi api ) {
        _api = api;
    }

    @Override
    public User login(String email, String password) {
        try {
            Login result = _api.login(email, password);

            User user = new User();
            user.setId(result.getUserId());
            user.setAccessToken(result.getAccessToken());
            return user;
        }
        catch(ApiCodedException exception){
            throw exception;
        }
    }
}
