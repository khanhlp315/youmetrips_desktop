package com.phuongkhanh.youmetrips.services.components;

import com.phuongkhanh.youmetrips.presentation.components.login.LoginService;
import com.phuongkhanh.youmetrips.presentation.exceptions.*;
import com.phuongkhanh.youmetrips.presentation.models.User;
import com.phuongkhanh.youmetrips.services.api.RestApi;
import com.phuongkhanh.youmetrips.services.api.exceptions.*;
import com.phuongkhanh.youmetrips.services.api.models.Login;
import com.phuongkhanh.youmetrips.services.api.models.SignUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
                throw new WrongEmailOrPasswordException();
            }
            if(exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.UserNotConfirmedException")){
                throw new UserNotConfirmedException();
            }
            throw exception;
        }
    }

    @Override
    public void signUp(String emailOrPhone, String password, String firstName, String lastName) {
        try {
            SignUp newUser = _api.signUp(emailOrPhone, password, firstName, lastName);
            System.out.println(newUser.getConfirmToken());
        }
        catch (ApiCodedException exception)
        {
            if(exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.AlreadyUsedEmailOrPhoneNumberException")){
                throw new AlreadyUsedEmailOrPhoneNumberException();
            }
            if(exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidEmailOrPhoneNumberException")){
                throw new InvalidEmailOrPhoneNumberException();
            }
            if(exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidPasswordException")){
                throw new InvalidPasswordException();
            }
            if(exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidUserFirstNameException")){
                throw new InvalidUserFirstNameException();
            }
            if(exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidUserLastNameException")){
                throw new InvalidUserLastNameException();
            }
            throw exception;
        }
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
            if(exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidFacebookAccessTokenException")){
                throw new InvalidFacebookAccessTokenException();
            }
            throw exception;
        }
    }

    @Override
        public String getAccessToken()
    {
        String domain = "https://www.google.com.vn/";
        String appId = "2046554585621498";

        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=public_profile";

        System.setProperty("webdriver.chrome.driver","chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;

        while (true)
        {
            if(!driver.getCurrentUrl().contains("facebook.com"))
            {
                String url = driver.getCurrentUrl();
                accessToken = url.replaceAll(".*#access_token=(.+)&.*","$1");
                driver.quit();
                return accessToken;
            }
        }
    }
}
