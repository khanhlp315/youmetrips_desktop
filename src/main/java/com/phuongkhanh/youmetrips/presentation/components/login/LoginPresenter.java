package com.phuongkhanh.youmetrips.presentation.components.login;

import com.phuongkhanh.youmetrips.presentation.exceptions.*;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.presentation.models.User;
import com.phuongkhanh.youmetrips.services.api.models.Login;
import com.phuongkhanh.youmetrips.services.api.models.SignUp;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import javafx.concurrent.Task;
import org.openqa.selenium.WebDriverException;

import javax.inject.Inject;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.validateEmail;
import static com.phuongkhanh.youmetrips.utils.CommonUtils.validatePhoneNumber;

public class LoginPresenter extends PresenterBase<LoginScreen> {
    private final LoginService _service;

    /*
     * login                <-> state = 1;
     * login With Facebook  <-> state = 2;
     */
    private int _state = 1;

    @Inject
    public LoginPresenter(LoginService service) {
        _service = service;
    }

    public void requestToSignup() {
        getView().navigateToSignup();
    }

    public void login(String email, String password) {
        // cho view loading
        setState(1);
        getView().setLoading(true);

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
                        getView().setLoading(false);
                    }

                    @Override
                    protected void failed() {
                        onLoginFailed(getException());
                        getView().setLoading(false);
                    }
                }
        ).start();
    }


    public void loginWithFB() {
        setState(2);
        getView().setLoading(true);

        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        //doLoginWithFB(_service.getAccessToken());
                        doLoginWithFB(_service.getAccessToken());
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        onLoginSuccess();
                        getView().setLoading(false);
                    }

                    @Override
                    protected void failed() {
                        onLoginFailed(getException());
                        getView().setLoading(false);
                    }
                }
        ).start();
    }


    private void doLogin(String email, String password) {
        if (!(validatePhoneNumber(email) || validateEmail(email))) {
            throw new InvalidEmailException();
        } else if (password.trim().length() < 6 || password.trim().length() > 20) {
            throw new InvalidPasswordException();
        }

        Login login = _service.login(email, password);

        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        authenticationStore.storeLoginData(login.getUserId(), login.getJwt());
    }

    private void doLoginWithFB(String accessToken) {
        // check invalid accessToken
        // can not connect to FB server exception
        Login login = _service.loginWithFB(accessToken);
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        authenticationStore.storeLoginData(login.getUserId(), login.getJwt());

    }

    private void onLoginFailed(final Throwable ex) {
        if (ex instanceof InvalidEmailException) {
            getView().showError(ex.getMessage());
        } else if (ex instanceof UserNotConfirmedException) {

            getView().showError("Please confirm your email");
            getView().navigateToSignUpConfirmationCodeScreen();
        } else if (ex instanceof WrongEmailOrPasswordException) {
            getView().showError("Email/Phone or Password is incorrect");
        } else if (ex instanceof InvalidFacebookAccessTokenException) {
            getView().showError("Can not login with facebook");
        } else if (ex instanceof WebDriverException) {
            getView().showError("Quit WebDriver");
        }
    }

    private void onLoginSuccess() {

    }

    private void setState(int state) {
        _state = state;
    }

    public int getState() {
        return _state;
    }
}
