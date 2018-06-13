package com.phuongkhanh.youmetrips.services.api.models;

/*
 * @author by LeVoGiaKhang
 */
public class SignUp {

    private int userId;
    private String confirmToken;
    private String resendConfirmationCodeToken;

    public SignUp() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getConfirmToken() {
        return confirmToken;
    }

    public void setConfirmToken(String confirmToken) {
        this.confirmToken = confirmToken;
    }

    public String getResendConfirmationCodeToken() {
        return resendConfirmationCodeToken;
    }

    public void setResendConfirmationCodeToken(String resendConfirmationCodeToken) {
        this.resendConfirmationCodeToken = resendConfirmationCodeToken;
    }
}
