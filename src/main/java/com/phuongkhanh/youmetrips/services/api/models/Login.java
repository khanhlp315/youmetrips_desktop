package com.phuongkhanh.youmetrips.services.api.models;

public class Login {
    private int _userId;
    private String _accessToken;

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
}
