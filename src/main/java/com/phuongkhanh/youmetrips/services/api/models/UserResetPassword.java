package com.phuongkhanh.youmetrips.services.api.models;

/*
 * @author by LeVoGiaKhang
 */
public class UserResetPassword {
    private int userId;
    private String resetPasswordToken;

    public UserResetPassword() {
    }

    public void setUserId(int id)
    {
        userId = id;
    }

    public void setUserToken(String token)
    {
        resetPasswordToken = token;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getUserToken()
    {
        return resetPasswordToken;
    }
}
