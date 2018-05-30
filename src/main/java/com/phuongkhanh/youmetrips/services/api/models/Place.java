package com.phuongkhanh.youmetrips.services.api.models;

import java.util.List;

public class Place {
    private int id;
    private String name;
    private String coverImageUrl;
    private String location;
    private List<List> tags;
    private List<List> reviews;
    private List<List> likes;
    private boolean liked;
    private int numberOfLikes = 0;
    private int numberOfPeopleGoing = 0;


    public int getId()
    {
        return id;
    }

    public void setId(int newId)
    {
        id = newId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<List> getTags() {
        return tags;
    }

    public void setTags(List<List> tags) {
        this.tags = tags;
    }

    public List<List> getReviews() {
        return reviews;
    }

    public void setReviews(List<List> reviews) {
        this.reviews = reviews;
    }

    public List<List> getLikes() {
        return likes;
    }

    public void setLikes(List<List> likes) {
        this.likes = likes;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
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
}
