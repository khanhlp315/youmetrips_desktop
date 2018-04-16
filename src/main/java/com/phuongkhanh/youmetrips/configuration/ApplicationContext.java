package com.phuongkhanh.youmetrips.configuration;

import com.phuongkhanh.youmetrips.presentation.components.login.LoginPresenter;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginScreen;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginService;
import com.phuongkhanh.youmetrips.presentation.windows.LoginWindow;
import com.phuongkhanh.youmetrips.services.components.LoginServiceImpl;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        ApplicationContext.PresentationModule.class,
        ApplicationContext.ServiceModule.class
})

public interface ApplicationContext {
    LoginWindow loginWindow();

    @Module
    class PresentationModule {
        @Provides
        static LoginScreen loginScreen(LoginPresenter presenter) {
            return new LoginScreenImpl(presenter);
        }

        @Provides
        static LoginPresenter loginPresenter(LoginService loginService) {
            return new LoginPresenter(loginService);
        }

    }

    @Module
    class ServiceModule {
        @Provides
        static LoginService loginService() {
            return new LoginServiceImpl();
        }

    }
}
