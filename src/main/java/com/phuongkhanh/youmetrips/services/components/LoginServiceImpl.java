package com.phuongkhanh.youmetrips.services.components;

import com.phuongkhanh.youmetrips.presentation.components.login.LoginService;
import com.phuongkhanh.youmetrips.presentation.exceptions.InvalidEmailException;
import com.phuongkhanh.youmetrips.presentation.models.User;
import com.phuongkhanh.youmetrips.services.api.RestApi;
import com.phuongkhanh.youmetrips.services.api.exceptions.ApiCodedException;
import com.phuongkhanh.youmetrips.services.api.models.Login;
import com.phuongkhanh.youmetrips.services.api.models.SignUp;

public class LoginServiceImpl implements LoginService {
    private final RestApi _api;

    public LoginServiceImpl(final RestApi api) {
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
        } catch (ApiCodedException exception) {
            if(exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.WrongEmailOrPasswordException")){
                //throw new WrongEmailOrPasswordException();
            }
            throw exception;
        }
    }

    @Override
    public void signUp(String emailOrPhone, String password, String firstName, String lastName) {
        SignUp newUser = _api.signUp(emailOrPhone, password, firstName, lastName);
        System.out.println(newUser.getConfirmToken());
    }

    @Override
    public User loginWithFB(String accessToken) {
        try {
            Login result = _api.loginWithFB(accessToken);

            User user = new User();
            user.setId(result.getUserId());
            user.setUserLastName(result.getUserLastName());
            user.setUserFirstName(result.getUserFirstName());
            return user;
        } catch (ApiCodedException exception) {
            throw exception;
        }
    }
}
