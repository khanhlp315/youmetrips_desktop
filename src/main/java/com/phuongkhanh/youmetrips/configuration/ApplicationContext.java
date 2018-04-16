package com.phuongkhanh.youmetrips.configuration;

import com.phuongkhanh.youmetrips.presentation.components.login.LoginPresenter;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginScreen;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginService;
import com.phuongkhanh.youmetrips.presentation.components.signup.SignUpPresenter;
import com.phuongkhanh.youmetrips.presentation.components.signup.SignUpScreen;
import com.phuongkhanh.youmetrips.presentation.components.signup.SignUpScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.signup.SignUpService;
import com.phuongkhanh.youmetrips.presentation.windows.LoginWindow;
import com.phuongkhanh.youmetrips.services.api.RestApi;
import com.phuongkhanh.youmetrips.services.api.models.SignUp;
import com.phuongkhanh.youmetrips.services.components.LoginServiceImpl;
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

    }

    @Module
    static class ServiceModule{
        @Provides
        static LoginService loginService(  ) {
            return new LoginServiceImpl(restApi());
        }

        @Provides
        @Singleton
        static RestApi restApi() {
            return new RestApi();
        }
    }
}
