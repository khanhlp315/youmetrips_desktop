package com.phuongkhanh.youmetrips.presentation.components.login;

import com.phuongkhanh.youmetrips.presentation.exceptions.*;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.presentation.models.User;
import javafx.concurrent.Task;

import javax.inject.Inject;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.validateEmail;

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

    public void signUp(String emailOrPhone,
                       String password,
                       String confirmPassword,
                       String firstName,
                       String lastName) {
        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        doSignUp(emailOrPhone, password, confirmPassword, firstName, lastName);
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        onSignUpSuccess();
                    }

                    @Override
                    protected void failed() {
                        onSignUpFailed(getException());
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

    public void doSignUp(String emailOrPhone,
                          String password,
                          String confirmPassword,
                          String firstName,
                          String lastName) {
        if (!validateEmail(emailOrPhone)) {
            throw new InvalidEmailException();
        }

        if (!password.equals(confirmPassword)) {
            throw new ConfirmPasswordNotMatchException();
        }

        if (emailOrPhone.trim().isEmpty() || password.trim().isEmpty() || firstName.trim().isEmpty() || lastName.trim().isEmpty()) {
            throw new EmptyFieldException();
        }

        _service.signUp(emailOrPhone, password, firstName, lastName);
    }


    private void doLogin(String email, String password) {
        if (!validateEmail(email)) {
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
        System.out.println("Login failed");
        if(ex instanceof InvalidEmailException){
            getView().showError(ex.getMessage());
        }

        if(ex instanceof UserNotConfirmedException){
            getView().showError("Please confirm your email");
        }

        if(ex instanceof WrongEmailOrPasswordException)
        {
            getView().showError("Email/Phone or Password is incorrect");
        }

        if(ex instanceof InvalidFacebookAccessTokenException)
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
        if(ex instanceof AlreadyUsedEmailOrPhoneNumberException)
        {
            getView().showError("This email has already created");
        }
        if(ex instanceof ConfirmPasswordNotMatchException)
        {
            getView().showError("Confirm password is not match");
        }
        if(ex instanceof EmptyFieldException)
        {
            getView().showError("Please fill in all fields");
        }
        if(ex instanceof InvalidEmailOrPhoneNumberException)
        {
            getView().showError("Email or Phone number is incorrect");
        }
        if(ex instanceof InvalidPasswordException)
        {
            getView().showError("Password must have at least 6 and at most 20 characters!");
        }
        if(ex instanceof InvalidUserFirstNameException)
        {
            getView().showError("Please fill in first name");
        }
        if(ex instanceof InvalidUserLastNameException)
        {
            getView().showError("Please fill in last name");
        }

    }

    private void onSignUpSuccess() {
        getView().showSuccess("Sign up success");
        // xu ly sign up success
        // go to Confirm Code Screen
    }


}
