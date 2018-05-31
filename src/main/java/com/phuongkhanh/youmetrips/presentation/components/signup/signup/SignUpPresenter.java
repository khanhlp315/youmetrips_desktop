package com.phuongkhanh.youmetrips.presentation.components.signup.signup;

import com.jfoenix.controls.JFXButton;
import com.phuongkhanh.youmetrips.presentation.exceptions.*;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.presentation.models.User;
import com.phuongkhanh.youmetrips.services.api.models.SignUp;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;

import javax.inject.Inject;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.validateEmail;

/*
 * @author by LeVoGiaKhang
 */
public class SignUpPresenter extends PresenterBase<SignUpScreen> {
    private final SignUpService _service;

    @Inject
    public SignUpPresenter(SignUpService service) {
        _service = service;
    }

    public void signUp(String emailOrPhone,
                       String password,
                       String confirmPassword,
                       String firstName,
                       String lastName) {

        getView().setLoading(true);

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
                        getView().setLoading(false);
                    }

                    @Override
                    protected void running() {
                        onRunning();
                        getView().setLoading(false);
                    }
                }
        ).start();
    }

    public void requestToNavigateBackLogin()
    {
        getView().onNavigateBackLogin();
    }

    private void doSignUp(String emailOrPhone,
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

        SignUp signUp = _service.signUp(emailOrPhone, password, firstName, lastName);
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        authenticationStore.storeSignupData(signUp.getUserId(), signUp.getConfirmToken(), signUp.getResendConfirmationCodeToken());
    }

    private void onSignUpSuccess() {
        getView().onNavigateToSignUpReceiveCode();
        // xu ly sign up success
        // go to Confirm Code Screen
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

    private void onRunning()
    {
        getView().showLoading();
    }


}
