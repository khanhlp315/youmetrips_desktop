package com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code;

/*
 * @author by LeVoGiaKhang
 */
public interface SignUpConfirmationCodeService {

    void sendConfirmationCode(String verifyCode);
    void resendConfirmationCode();
}
