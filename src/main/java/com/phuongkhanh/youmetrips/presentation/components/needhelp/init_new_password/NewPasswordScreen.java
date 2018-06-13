package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

/*
 * @author by LeVoGiaKhang
 */
public interface NewPasswordScreen extends JFXScreen {
    void showError(String message);
    void showSuccess(String message);
    void showLoading();
    void navigateToLoginScreen();
    void onNavigateBack();
}
