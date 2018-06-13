package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code;

import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;

/*
 * @author by LeVoGiaKhang
 */
public interface NewPasswordInitCodeService {
    String sendCodeToResetPassword(String recoveryCode, int userId);

    void resendCodeToResetPassword();

    AuthenticationStore getAuthenticationStore();
}
