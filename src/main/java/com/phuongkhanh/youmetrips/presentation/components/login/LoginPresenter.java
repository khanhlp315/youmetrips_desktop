package com.phuongkhanh.youmetrips.presentation.components.login;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;

import javax.inject.Inject;

public class LoginPresenter extends PresenterBase<LoginScreen> {
    private final LoginService _service;

    @Inject
    public LoginPresenter( LoginService service ) {
        _service = service;
    }

    public void requestToSignup() {
        getView().navigateToSignup();
    }

    public void loginWithFacebook(){
        String token = "";
        _service.loginWithFacebook(token);
    }

    public void loginWithEmailPassword()
    {
        _service.loginWithEmailPassword();
    }

    public void signup()
    {
        _service.signup();
    }

}
