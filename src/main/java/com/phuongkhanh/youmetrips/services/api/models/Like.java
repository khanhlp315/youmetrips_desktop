package com.phuongkhanh.youmetrips.services.api.models;

public class Like {
    private int likerId = 0;
    private String likerFirstName;
    private String likerLastName;
    private String likerAvatarUrl;

    public int getLikerId() {
        return likerId;
    }

    public void setLikerId(int likerId) {
        this.likerId = likerId;
    }

    public String getLikerFirstName() {
        return likerFirstName;
    }

    public void setLikerFirstName(String likerFirstName) {
        this.likerFirstName = likerFirstName;
    }

    public String getLikerLastName() {
        return likerLastName;
    }

    public void setLikerLastName(String likerLastName) {
        this.likerLastName = likerLastName;
    }

    public String getLikerAvatarUrl() {
        return likerAvatarUrl;
    }

    public void setLikerAvatarUrl(String likerAvatarUrl) {
        this.likerAvatarUrl = likerAvatarUrl;
    }
}