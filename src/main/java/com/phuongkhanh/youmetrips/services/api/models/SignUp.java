package com.phuongkhanh.youmetrips.services.api.models;

/*
 * @author by LeVoGiaKhang
 */
public class SignUp {
    private int _userId;
    private String _confirmToken;
    private String _resendConfirmationCodeToken;
    private String _userMessageDict;



    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }



    public String getConfirmToken() {
        return _confirmToken;
    }

    public void setConfirmToken(String confirmToken) {
        _confirmToken = confirmToken;
    }



    public String getResendConfirmationCodeToken() {
        return _resendConfirmationCodeToken;
    }

    public void setResendConfirmationCodeToken(String resendConfirmationCodeToken) {
        _resendConfirmationCodeToken = resendConfirmationCodeToken;
    }



    public String getUserMessageDict() {
        return _userMessageDict;
    }

    public void setUserMessageDict(String userMessageDict) {
        _userMessageDict = userMessageDict;
    }
}
