package com.phuongkhanh.youmetrips.presentation.components.login;

import com.phuongkhanh.youmetrips.presentation.exceptions.*;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.presentation.models.User;
import javafx.concurrent.Task;

import javax.inject.Inject;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.validateEmail;
import static com.phuongkhanh.youmetrips.utils.CommonUtils.validatePhoneNumber;

public class LoginPresenter extends PresenterBase<LoginScreen> {
    private final LoginService _service;
    private User _currentUser;

    @Inject
    public LoginPresenter(LoginService service) {
        _service = service;
    }

    public void requestToSignup() {
        getView().navigateToSignup();
    }

    public void login(String email, String password) {
        // cho view loading

        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        doLogin(email, password);
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        onLoginSuccess();
                    }

                    @Override
                    protected void failed() {
                        onLoginFailed(getException());
                    }
                }
        ).start();
    }


    public void loginWithFB() {
        // cho view loading

        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        doLoginWithFB(_service.getAccessToken());
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        onLoginSuccess();
                    }

                    @Override
                    protected void failed() {
                        onLoginFailed(getException());
                    }
                }
        ).start();
    }


    private void doLogin(String email, String password) {
        if (!(validatePhoneNumber(email) || validateEmail(email)))
        {
            throw new InvalidEmailException();
        }
        _currentUser = _service.login(email, password);
    }

    private void doLoginWithFB(String accessToken)
    {
        // check invalid accessToken
        // can not connect to FB server exception
        User user = _service.loginWithFB(accessToken);
    }

    private void onLoginFailed(final Throwable ex) {
        if(ex instanceof InvalidEmailException){
            getView().showError(ex.getMessage());
        }

        else if(ex instanceof UserNotConfirmedException){
            getView().showError("Please confirm your email");
        }

        else if(ex instanceof WrongEmailOrPasswordException)
        {
            getView().showError("Email/Phone or Password is incorrect");
        }

        else if(ex instanceof InvalidFacebookAccessTokenException)
        {
            getView().showError("Can not login with facebook");
        }
    }

    private void onLoginSuccess() {
        getView().showSuccess("Hello " + _currentUser.getUserLastName() + " " + _currentUser.getUserFirstName());
    }






}
