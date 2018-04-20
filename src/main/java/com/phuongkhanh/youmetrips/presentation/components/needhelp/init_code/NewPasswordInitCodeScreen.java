package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

/*
 * @author by LeVoGiaKhang
 */
public interface NewPasswordInitCodeScreen extends JFXScreen {
    void showError(String message);
    void showSuccess(String message);
    void showLoading();
    void navigateToInputNewPasswordScreen();
}
