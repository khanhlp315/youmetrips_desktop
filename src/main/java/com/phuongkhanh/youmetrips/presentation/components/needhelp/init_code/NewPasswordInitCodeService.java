package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code;

/*
 * @author by LeVoGiaKhang
 */
public interface NewPasswordInitCodeService {
    public void sendCodeToResetPassword(String recoveryCode);
    public void resendCodeToResetPassword();
}
