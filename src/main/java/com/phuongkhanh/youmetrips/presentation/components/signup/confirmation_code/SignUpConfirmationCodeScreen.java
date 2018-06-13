package com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

/*
 * @author by LeVoGiaKhang
 */
public interface SignUpConfirmationCodeScreen extends JFXScreen {
    void showError(String message);
    void showSuccess(String message);
    void showLoading();
    void onNavigateToHome();
    void onNavigateBackSignUpScreen();
}