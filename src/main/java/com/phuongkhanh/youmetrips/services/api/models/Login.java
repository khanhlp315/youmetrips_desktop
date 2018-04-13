package com.phuongkhanh.youmetrips.services.api.models;

public class Login {
    private int _userId;
    private String _accessToken;
    private String _userFirstName;
    private String _userLastName;

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public String getAccessToken() {
        return _accessToken;
    }

    public void setAccessToken(String accessToken) {
        _accessToken = accessToken;
    }

    public String getUserLastName() {return _userLastName;}

    public void setUserLastName(String userLastName) {_userLastName = userLastName;}

    public String getUserFirstName() {return _userFirstName;}

    public void setUserFirstName(String userFirstName) {_userFirstName = userFirstName;}
}
