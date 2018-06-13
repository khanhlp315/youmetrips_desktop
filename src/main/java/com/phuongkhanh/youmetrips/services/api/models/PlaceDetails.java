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

class Tag {
    private String tag;
    private int totalVote = 0;
    private int myVote = 0;


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(int totalVote) {
        this.totalVote = totalVote;
    }

    public int getMyVote() {
        return myVote;
    }

    public void setMyVote(int myVote) {
        this.myVote = myVote;
    }
}

class Review {
    private int rate = 0;
    private int reviewerId = 0;
    private String message;
    private String reviewerFirstName;
    private String reviewerLastName;
    private String reviewerAvatarUrl;
    private String reviewedTime;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReviewerFirstName() {
        return reviewerFirstName;
    }

    public void setReviewerFirstName(String reviewerFirstName) {
        this.reviewerFirstName = reviewerFirstName;
    }

    public String getReviewerLastName() {
        return reviewerLastName;
    }

    public void setReviewerLastName(String reviewerLastName) {
        this.reviewerLastName = reviewerLastName;
    }

    public String getReviewerAvatarUrl() {
        return reviewerAvatarUrl;
    }

    public void setReviewerAvatarUrl(String reviewerAvatarUrl) {
        this.reviewerAvatarUrl = reviewerAvatarUrl;
    }

    public String getReviewedTime() {
        return reviewedTime;
    }

    public void setReviewedTime(String reviewedTime) {
        this.reviewedTime = reviewedTime;
    }
}

class Like {
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