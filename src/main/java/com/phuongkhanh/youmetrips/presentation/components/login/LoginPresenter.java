package com.phuongkhanh.youmetrips.presentation.components.login;

import com.phuongkhanh.youmetrips.presentation.exceptions.*;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.presentation.models.User;
import javafx.concurrent.Task;
import org.openqa.selenium.WebDriverException;

import javax.inject.Inject;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.validateEmail;
import static com.phuongkhanh.youmetrips.utils.CommonUtils.validatePhoneNumber;

public class LoginPresenter extends PresenterBase<LoginScreen> {
    private final LoginService _service;
    private User _currentUser;

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

                    @Override
                    protected void running(){
                        setState(1);
                        onRunning();
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
                        //doLoginWithFB(_service.getAccessToken());
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

                    @Override
                    protected void running(){
                        setState(2);
                        onRunning();
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
        _currentUser = _service.loginWithFB(accessToken);
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
        else if(ex instanceof WebDriverException)
        {
            getView().showError("Quit WebDriver");
        }
    }

    private void onLoginSuccess() {
        getView().showSuccess("Hello " + _currentUser.getUserLastName() + " " + _currentUser.getUserFirstName());
    }

    private void onRunning()
    {
        getView().showLoading();
    }

    private void setState(int state)
    {
        _state = state;
    }

    public int getState()
    {
        return _state;
    }
}
