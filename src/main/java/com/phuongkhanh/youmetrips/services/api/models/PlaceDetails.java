package com.phuongkhanh.youmetrips.services.api.models;

import java.util.List;

public class PlaceDetails {
    private int id;
    private String name;
    private String coverImageUrl;
    private String location;
    private List<Tag> tags;
    private List<Review> reviews;
    private List<Like> likes;
    private boolean liked;
    private int numberOfLikes = 0;
    private int numberOfPeopleGoing = 0;


    public int getId() {
        return id;
    }

    public void setId(int newId) {
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}

