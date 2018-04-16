package com.phuongkhanh.youmetrips.presentation.components.signup;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

/*
 * @author by LeVoGiaKhang
 */
public interface SignUpScreen extends JFXScreen {
    void showError(String message);
    void showSuccess(String message);
    void showLoading();
}
