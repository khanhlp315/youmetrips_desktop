package com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code;

import com.phuongkhanh.youmetrips.services.api.models.Login;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;

/*
 * @author by LeVoGiaKhang
 */
public interface SignUpConfirmationCodeService {

    Login sendConfirmationCode(String verifyCode, int userId, String token);

    void resendConfirmationCode(int userId, String token);

    AuthenticationStore getAuthenticationStore();
}
