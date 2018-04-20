package com.phuongkhanh.youmetrips.services.api.models;

public class Login {
    private int userId;
    private String accessToken;
    private String userFirstName;
    private String userLastName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserLastName() {return userLastName;}

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;}

    public String getUserFirstName() {return userFirstName;}

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;}
}
