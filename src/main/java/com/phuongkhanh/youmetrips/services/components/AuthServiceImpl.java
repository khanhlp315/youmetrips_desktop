package com.phuongkhanh.youmetrips.services.components;

import com.phuongkhanh.youmetrips.presentation.components.login.LoginService;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code.NewPasswordInitCodeService;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email.NewPasswordInitEmailService;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password.NewPasswordService;
import com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code.SignUpConfirmationCodeService;
import com.phuongkhanh.youmetrips.presentation.components.signup.signup.SignUpService;
import com.phuongkhanh.youmetrips.presentation.exceptions.*;
import com.phuongkhanh.youmetrips.presentation.models.User;
import com.phuongkhanh.youmetrips.services.api.RestApi;
import com.phuongkhanh.youmetrips.services.api.exceptions.*;
import com.phuongkhanh.youmetrips.services.api.models.Login;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AuthServiceImpl implements LoginService,
        SignUpService,
        SignUpConfirmationCodeService,
        NewPasswordInitEmailService,
        NewPasswordInitCodeService,
        NewPasswordService {
    private final RestApi _api;


    public AuthServiceImpl(final RestApi api) {
        _api = api;
    }

    @Override
    public User login(String email, String password) {
        try {
            Login result = _api.login(email, password);

            User user = new User();
            user.setId(result.getUserId());
            user.setUserFirstName(result.getUserFirstName());
            user.setUserLastName(result.getUserLastName());
            return user;
        } catch (ApiCodedException exception) {
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.WrongEmailOrPasswordException")) {
                throw new WrongEmailOrPasswordException();
            }
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.UserNotConfirmedException")) {
                throw new UserNotConfirmedException();
            }
            throw exception;
        }
    }

    @Override
    public void signUp(String emailOrPhone, String password, String firstName, String lastName) {
        try {
            _api.signUp(emailOrPhone, password, firstName, lastName);
        } catch (ApiCodedException exception) {
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.AlreadyUsedEmailOrPhoneNumberException")) {
                throw new AlreadyUsedEmailOrPhoneNumberException();
            }
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidEmailOrPhoneNumberException")) {
                throw new InvalidEmailOrPhoneNumberException();
            }
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidPasswordException")) {
                throw new InvalidPasswordException();
            }
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidUserFirstNameException")) {
                throw new InvalidUserFirstNameException();
            }
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidUserLastNameException")) {
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
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidFacebookAccessTokenException")) {
                throw new InvalidFacebookAccessTokenException();
            }
            throw exception;
        }
    }


    @Override
    public String getAccessToken() {
        String domain = "https://www.google.com.vn/";
        String appId = "2046554585621498";

        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=public_profile";

        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken = "";
        try {
            while (true) {
                if (!driver.getCurrentUrl().contains("facebook.com")) {
                    String url = driver.getCurrentUrl();
                    accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                    driver.quit();
                    return accessToken;
                }
            }
        } catch (WebDriverException e) { //thrown after can't reach browser (browser closed)
            //handle exception
            throw e;
        } finally {
            if (driver != null)
                driver.quit();
        }
    }

    @Override
    public void sendConfirmationCode(String confirmationCode) {
        try {
            _api.sendConfirmationCode(confirmationCode);
        } catch (ApiCodedException exception) {
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")) {
                throw new InvalidJwtException();
            }
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.WrongConfirmationCodeException")) {
                throw new WrongConfirmationCodeException();
            }
            throw exception;
        }
    }

    @Override
    public void resendConfirmationCode() {
        try {
            _api.resendConfirmationCOde();
        } catch (ApiCodedException exception) {
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")) {
                throw new InvalidJwtException();
            }
            throw exception;
        }
    }


    @Override
    public void sendEmailToResetPassword(String email) {
        try {
            _api.sendEmailToResetPassword(email);
        } catch (ApiCodedException exception) {
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.NotFoundUserEmailException")) {
                throw new NotFoundUserEmailException();
            }
            throw exception;
        }
    }

    @Override
    public void sendCodeToResetPassword(String recoveryCode) {
        try {
            _api.sendCodeToResetPassword(recoveryCode);
        } catch (ApiCodedException ex) {
            if (ex.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.WrongRecoveryCodeException")) {
                throw new WrongRecoveryCodeException();
            }
            throw ex;
        }
    }

    @Override
    public void resendCodeToResetPassword() {
        try
        {
            _api.resendCodeToResetPassword();
        }
        catch (ApiCodedException exception)
        {
            throw exception;
        }
    }


    @Override
    public void sendPasswordToResetPassword(String newPassword) {
        try {
            _api.sendPasswordToResetPassword(newPassword);
        } catch (ApiCodedException exception) {
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")) {
                throw new ExpiredJwtException();
            }
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidPasswordException")) {
                throw new InvalidPasswordException();
            }
            throw exception;
        }
    }
}
