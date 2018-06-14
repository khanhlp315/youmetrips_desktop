package com.phuongkhanh.youmetrips.presentation.models;

public class User {
    private int _id;
    private String _avatar;
    private String _userFirstName;
    private String _userLastName;

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

    public String getUserLastName() {
        return _userLastName;
    }

    public void setUserLastName(String userLastName) {
        _userLastName = userLastName;
    }

    public String getUserFirstName() {
        return _userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        _userFirstName = userFirstName;
    }
}
