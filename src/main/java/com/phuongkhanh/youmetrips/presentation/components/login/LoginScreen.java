package com.phuongkhanh.youmetrips.presentation.components.login;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

public interface LoginScreen extends JFXScreen {
    void navigateToSignup();
    void showError(String message);
    void showSuccess(String message);
    void showLoading();
    void setLoading(Boolean isLoading);
    void navigateToSignUpConfirmationCodeScreen();
    void navigateToHome();
}
