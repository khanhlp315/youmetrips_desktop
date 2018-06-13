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
import com.phuongkhanh.youmetrips.services.api.models.ApiError;
import com.phuongkhanh.youmetrips.services.api.models.Login;
import com.phuongkhanh.youmetrips.services.api.models.SignUp;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
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
import java.util.Map;

public class AuthServiceImpl implements LoginService,
        SignUpService,
        SignUpConfirmationCodeService,
        NewPasswordInitEmailService,
        NewPasswordInitCodeService,
        NewPasswordService {
    private final RestApi _api;
    private final AuthenticationStore _authenticationStore;

    public AuthServiceImpl(final RestApi api, final AuthenticationStore authenticationStore) {
        _api = api;
        _authenticationStore = authenticationStore;
    }

    @Override
    public Login login(String email, String password) {
        try {
            return _api.login(email, password);
        } catch (ApiCodedException exception) {
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.WrongEmailOrPasswordException")) {
                throw new WrongEmailOrPasswordException();
            }
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.UserNotConfirmedException")) {
                SignUp signUp = new SignUp();
                Map<String, Object> data = exception.getError().getData();
                signUp.setUserId(((Double)data.get("userId")).intValue());
                signUp.setConfirmToken((String)data.get("confirmToken"));
                signUp.setResendConfirmationCodeToken((String)data.get("resendConfirmationCodeToken"));
                throw new UserNotConfirmedException(signUp);
            }
            throw exception;
        }
    }

    @Override
    public SignUp signUp(String emailOrPhone, String password, String firstName, String lastName) {
        try {
            return _api.signUp(emailOrPhone, password, firstName, lastName);
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
    public Login loginWithFB(String accessToken) {
        try {
            return _api.loginWithFB(accessToken);
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
    public AuthenticationStore getAuthenticationStore() {
        return _authenticationStore;
    }

    @Override
    public Login sendConfirmationCode(String confirmationCode, int userId, String jwt) {
        try {
            return _api.sendConfirmationCode(confirmationCode, userId, jwt);
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
    public void resendConfirmationCode(int userId, String token) {
        try {
            _api.resendConfirmationCode(userId, token);
        } catch (ApiCodedException exception) {
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")) {
                throw new InvalidJwtException();
            }
            throw exception;
        }
    }


    @Override
    public int sendEmailToResetPassword(String email) {
        try {
            return _api.sendEmailToResetPassword(email);
        } catch (ApiCodedException exception) {
            if (exception.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.NotFoundUserEmailException")) {
                throw new NotFoundUserEmailException();
            }
            throw exception;
        }
    }

    @Override
    public String sendCodeToResetPassword(String recoveryCode, int userId) {
        try {
            return _api.sendCodeToResetPassword(recoveryCode, userId);
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
    public Login resetPassword(String newPassword, int userId, String resetPasswordToken) {
        try {
            return _api.resetPassword(newPassword, userId, resetPasswordToken);
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
