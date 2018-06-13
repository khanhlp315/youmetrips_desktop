package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password;

import com.phuongkhanh.youmetrips.services.api.models.Login;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;

/*
 * @author by LeVoGiaKhang
 */
public interface NewPasswordService {
    public Login resetPassword(String newPassword, int userId, String resetPasswordToken);

    public AuthenticationStore getAuthenticationStore();
}
