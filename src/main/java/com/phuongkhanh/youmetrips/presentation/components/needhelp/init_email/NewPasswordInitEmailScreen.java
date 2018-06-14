package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

/*
 * @author by LeVoGiaKhang
 */
public interface NewPasswordInitEmailScreen extends JFXScreen {
    void showError(String message);

    void showSuccess(String message);

    void showLoading();

    void navigateToInputCode();

    void setLoading(Boolean isLoading);

    void onNavigateBack();
}
