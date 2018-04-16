package com.phuongkhanh.youmetrips.presentation.components.signup;

import com.phuongkhanh.youmetrips.presentation.exceptions.ConfirmPasswordNotMatchException;
import com.phuongkhanh.youmetrips.presentation.exceptions.EmptyFieldException;
import com.phuongkhanh.youmetrips.presentation.exceptions.InvalidEmailException;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import javafx.concurrent.Task;

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

    private void onSignUpSuccess() {
        getView().showSuccess("Sign up success");
        // xu ly sign up success
        // go to Confirm Code Screen
    }

    private void onSignUpFailed(final Throwable ex) {
        getView().showSuccess("Sign up success");
        // xu ly sign up success
        // go to Confirm Code Screen
    }

}
