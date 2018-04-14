package com.phuongkhanh.youmetrips.presentation.components.login;

import com.phuongkhanh.youmetrips.presentation.exceptions.ConfirmPasswordNotMatchException;
import com.phuongkhanh.youmetrips.presentation.exceptions.EmptyFieldException;
import com.phuongkhanh.youmetrips.presentation.exceptions.InvalidEmailException;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.presentation.models.User;
import javafx.concurrent.Task;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

import javax.inject.Inject;
import javax.swing.*;

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

//        new Thread(
//                new Task<Object>() {
//                    @Override
//                    protected Object call() throws Exception {
//                        doLoginWithFB(getAccessToken());
//                        return null;
//                    }
//
//                    @Override
//                    protected void succeeded() {
//                        onLoginSuccess();
//                    }
//
//                    @Override
//                    protected void failed() {
//                        onLoginFailed(getException());
//                    }
//                }
//        ).start();
    }

    public void doSignUp(String emailOrPhone,
                          String password,
                          String confirmPassword,
                          String firstName,
                          String lastName) {
//        if (!validateEmail(emailOrPhone)) {
//            throw new InvalidEmailException();
//        }

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
        if(ex instanceof InvalidEmailException){
            getView().showError(ex.getMessage());
        }
    }

    private void onLoginSuccess() {
        getView().showSuccess("Log in success");
    }

    private void onSignUpFailed(final Throwable ex) {
        // xu ly sign up fail
        // exception email has already
        JOptionPane.showMessageDialog(null, "Sign Up failed.");
    }

    private void onSignUpSuccess() {
        getView().showSuccess("Sign up success");
        // xu ly sign up success
        // go to Confirm Code Screen
    }

//    private String getAccessToken()
//    {
//        String domain = "https://www.google.com.vn/";
//        String appId = "2046554585621498";
//
//        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=public_profile";
//
//        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
//
//        WebDriver driver = new ChromeDriver();
//        driver.get(authUrl);
//        String accessToken;
//
//        while (true)
//        {
//            if(!driver.getCurrentUrl().contains("facebook.com"))
//            {
//                String url = driver.getCurrentUrl();
//                accessToken = url.replaceAll(".*#access_token=(.+)&.*","$1");
//                driver.quit();
//                return accessToken;
//            }
//        }
//    }
}
