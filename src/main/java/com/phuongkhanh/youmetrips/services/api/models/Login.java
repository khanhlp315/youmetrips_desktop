package com.phuongkhanh.youmetrips.services.api.models;

public class Login {
    private int userId;
    private String jwt;
    private String userFirstName;
    private String userLastName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String accessToken) {
        this.jwt = accessToken;
    }

    public String getUserLastName() {return userLastName;}

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;}

    public String getUserFirstName() {return userFirstName;}

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;}
}
