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
        if (!(validatePhoneNumber(email) && validateEmail(email)))
        {
            throw new InvalidEmailException();
        }
        User user = _service.login(email, password);
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
        getView().showSuccess("Log in success");
    }

    private void onSignUpFailed(final Throwable ex) {
        if(ex instanceof InvalidEmailException)
        {
            getView().showError("This email is invalid");
        }
        else if(ex instanceof AlreadyUsedEmailOrPhoneNumberException)
        {
            getView().showError("This email has already created");
        }
        else if(ex instanceof ConfirmPasswordNotMatchException)
        {
            getView().showError("Confirm password is not match");
        }
        else if(ex instanceof EmptyFieldException)
        {
            getView().showError("Please fill in all fields");
        }
        else if(ex instanceof InvalidEmailOrPhoneNumberException)
        {
            getView().showError("Email or Phone number is incorrect");
        }
        else if(ex instanceof InvalidPasswordException)
        {
            getView().showError("Password must have at least 6 and at most 20 characters!");
        }
        else if(ex instanceof InvalidUserFirstNameException)
        {
            getView().showError("Please fill in first name");
        }
        else if(ex instanceof InvalidUserLastNameException)
        {
            getView().showError("Please fill in last name");
        }

    }




}
