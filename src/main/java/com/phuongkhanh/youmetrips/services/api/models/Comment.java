package com.phuongkhanh.youmetrips.services.api.models;

public class Comment {
    private int userId;
    private String userFirstName;
    private String userLastName;
    private String userAvatarUrl;
    private String userNationalityUrl;
    private String comment;
    private String time;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public String getUserNationalityUrl() {
        return userNationalityUrl;
    }

    public void setUserNationalityUrl(String userNationalityUrl) {
        this.userNationalityUrl = userNationalityUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
