package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code;

import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;

/*
 * @author by LeVoGiaKhang
 */
public interface NewPasswordInitCodeService {
    public void sendCodeToResetPassword(String recoveryCode, int userId);
    public void resendCodeToResetPassword();

    public AuthenticationStore getAuthenticationStore();
}
