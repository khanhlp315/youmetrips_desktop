package com.phuongkhanh.youmetrips.presentation.windows;

import com.phuongkhanh.youmetrips.presentation.components.login.LoginScreen;
import com.phuongkhanh.youmetrips.presentation.framework.JFXWindowBase;

import javax.inject.Inject;


public class LoginWindow extends JFXWindowBase {
    @Inject
    public LoginWindow(final LoginScreen loginScreen){
        super(loginScreen);
    }
}
