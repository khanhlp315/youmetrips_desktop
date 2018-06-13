package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email;

import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;

/*
 * @author by LeVoGiaKhang
 */
public interface NewPasswordInitEmailService {
    int sendEmailToResetPassword(String email);

    AuthenticationStore getAuthenticationStore();
}
