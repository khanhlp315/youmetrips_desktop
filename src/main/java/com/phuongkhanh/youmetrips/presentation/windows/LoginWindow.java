package com.phuongkhanh.youmetrips.presentation.windows;

import com.phuongkhanh.youmetrips.presentation.components.login.LoginScreen;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code.NewPasswordInitCodeScreen;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email.NewPasswordInitEmailScreen;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password.NewPasswordScreen;
import com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code.SignUpConfirmationCodeScreen;
import com.phuongkhanh.youmetrips.presentation.components.signup.signup.SignUpScreen;
import com.phuongkhanh.youmetrips.presentation.framework.JFXWindowBase;

import javax.inject.Inject;


public class LoginWindow extends JFXWindowBase {
    @Inject
    public LoginWindow(final LoginScreen loginScreen,
                       final SignUpScreen signUpScreen,
                       final SignUpConfirmationCodeScreen signUpReceiveCodeScreen,
                       final NewPasswordInitEmailScreen newPasswordInitEmailScreen,
                       final NewPasswordInitCodeScreen newPasswordInitCodeScreen,
                       final NewPasswordScreen newPasswordScreen)
    {
        super(  loginScreen,
                signUpScreen,
                signUpReceiveCodeScreen,
                newPasswordInitEmailScreen,
                newPasswordInitCodeScreen,
                newPasswordScreen);
    }
}
