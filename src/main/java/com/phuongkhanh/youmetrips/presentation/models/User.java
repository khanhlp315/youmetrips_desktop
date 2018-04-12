package com.phuongkhanh.youmetrips.presentation.models;

public class User {
    private int _id;
    private String _avatar;
    private String _accessToken;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getAvatar() {
        return _avatar;
    }

    public void setAvatar(String avatar) {
        _avatar = avatar;
    }

    public String getAccessToken() {
        return _accessToken;
    }

    public void setAccessToken(String accessToken) {
        _accessToken = accessToken;
    }
}
