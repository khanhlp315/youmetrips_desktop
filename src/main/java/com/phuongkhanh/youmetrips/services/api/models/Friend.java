package com.phuongkhanh.youmetrips.services.api.models;

public class Friend {
    private int userId;
    private String userFirstName;
    private String userLastName;
    private String userAvatarUrl;


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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Friend) {
            Friend friend = (Friend) obj;
            return friend.userId == userId &&
                    (friend.userAvatarUrl == null || userAvatarUrl == null || friend.userAvatarUrl.equals(userAvatarUrl)) &&
                    friend.userFirstName.equals(userFirstName) &&
                    friend.userLastName.equals(userLastName);
        }

        return false;
    }
}
