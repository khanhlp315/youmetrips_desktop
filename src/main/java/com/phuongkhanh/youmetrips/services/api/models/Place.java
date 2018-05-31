package com.phuongkhanh.youmetrips.services.api.models;

import java.util.List;

public class Place {
    private int id;
    private String name;
    private String coverImageUrl;
    private int numberOfLikes;
    private int numberOfPeopleGoing;
    private boolean liked;
    private List<String> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getNumberOfPeopleGoing() {
        return numberOfPeopleGoing;
    }

    public void setNumberOfPeopleGoing(int numberOfPeopleGoing) {
        this.numberOfPeopleGoing = numberOfPeopleGoing;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}