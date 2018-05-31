package com.phuongkhanh.youmetrips.configuration;

import com.phuongkhanh.youmetrips.presentation.components.login.LoginPresenter;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginScreen;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginService;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code.NewPasswordInitCodePresenter;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code.NewPasswordInitCodeScreen;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code.NewPasswordInitCodeScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code.NewPasswordInitCodeService;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email.NewPasswordInitEmailPresenter;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email.NewPasswordInitEmailScreen;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email.NewPasswordInitEmailScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email.NewPasswordInitEmailService;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password.NewPasswordPresenter;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password.NewPasswordScreen;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password.NewPasswordScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password.NewPasswordService;
import com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code.SignUpConfirmationCodePresenter;
import com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code.SignUpConfirmationCodeScreen;
import com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code.SignUpConfirmationCodeScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code.SignUpConfirmationCodeService;
import com.phuongkhanh.youmetrips.presentation.components.signup.signup.SignUpPresenter;
import com.phuongkhanh.youmetrips.presentation.components.signup.signup.SignUpScreen;
import com.phuongkhanh.youmetrips.presentation.components.signup.signup.SignUpScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.signup.signup.SignUpService;
import com.phuongkhanh.youmetrips.presentation.windows.LoginWindow;
import com.phuongkhanh.youmetrips.services.api.RestApi;
import com.phuongkhanh.youmetrips.services.components.AuthServiceImpl;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Singleton
@Component( modules = {
        ApplicationContext.PresentationModule.class,
        ApplicationContext.ServiceModule.class
} )

public interface ApplicationContext {
    LoginWindow loginWindow();

    @Module
    static class PresentationModule{
        @Provides
        static LoginScreen loginScreen(LoginPresenter presenter ) {
            return new LoginScreenImpl( presenter );
        }

        @Provides
        static LoginPresenter loginPresenter(LoginService loginService ) {
            return new LoginPresenter( loginService );
        }

        @Provides
        static SignUpScreen signUpScreen(SignUpPresenter presenter ) {
            return new SignUpScreenImpl( presenter );
        }

        @Provides
        static SignUpPresenter signUpPresenter(SignUpService signUpService ) {
            return new SignUpPresenter( signUpService );
        }

        @Provides
        static SignUpConfirmationCodeScreen signUpReceiveCodeScreen(SignUpConfirmationCodePresenter presenter)
        {
            return new SignUpConfirmationCodeScreenImpl(presenter);
        }

        @Provides
        static SignUpConfirmationCodePresenter signUpReceiveCodePresenter(SignUpConfirmationCodeService service)
        {
            return new SignUpConfirmationCodePresenter(service);
        }
        
        @Provides
        static NewPasswordInitEmailScreen newPasswordInitEmailScreen(NewPasswordInitEmailPresenter presenter)
        {
            return new NewPasswordInitEmailScreenImpl(presenter);
        }
        
        @Provides
        static NewPasswordInitEmailPresenter newPasswordInitEmailPresenter(NewPasswordInitEmailService service)
        {
            return new NewPasswordInitEmailPresenter(service);
        }

        @Provides
        static NewPasswordInitCodeScreen newPasswordInitCodeScreen(NewPasswordInitCodePresenter presenter)
        {
            return new NewPasswordInitCodeScreenImpl(presenter);
        }

        @Provides
        static NewPasswordInitCodePresenter newPasswordInitCodePresenter(NewPasswordInitCodeService service)
        {
            return new NewPasswordInitCodePresenter(service);
        }

        @Provides
        static NewPasswordScreen newPasswordScreen(NewPasswordPresenter presenter)
        {
            return new NewPasswordScreenImpl(presenter);
        }

        @Provides
        static NewPasswordPresenter newPasswordPresenter(NewPasswordService service)
        {
            return new NewPasswordPresenter(service);
        }

    }

    @Module
    static class ServiceModule{
        static RestApi _restApi = new RestApi();
        static AuthenticationStore _authenticationStore = new AuthenticationStore();

        @Provides
        static LoginService loginService(  ) {
            return new AuthServiceImpl(restApi(), authenticationStore());
        }

        @Provides
        static SignUpService signUpService(){
            return new AuthServiceImpl(restApi(), authenticationStore());
        }

        @Provides
        static SignUpConfirmationCodeService signUpReceiveCodeService()
        {
            return new AuthServiceImpl(restApi(), authenticationStore());
        }

        @Provides
        static NewPasswordInitEmailService newPasswordInitEmailService()
        {
            return new AuthServiceImpl(restApi(), authenticationStore());
        }

        @Provides
        static NewPasswordInitCodeService newPasswordInitCodeService()
        {
            return new AuthServiceImpl(restApi(), authenticationStore());
        }

        @Provides
        static NewPasswordService newPasswordService()
        {
            return new AuthServiceImpl(restApi(), authenticationStore());
        }

        @Provides
        static RestApi restApi() {
            return _restApi;
        }

        @Provides
        static AuthenticationStore authenticationStore() { return _authenticationStore; }
    }
}
