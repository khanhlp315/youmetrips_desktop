package com.phuongkhanh.youmetrips.services.api.models;

import java.util.Date;

public class Review {
    private int rate = 0;
    private int reviewerId = 0;
    private String message;
    private String reviewerFirstName;
    private String reviewerLastName;
    private String reviewerAvatarUrl;
    private DateTime reviewedTime;

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

    public DateTime getReviewedTime() {
        return reviewedTime;
    }

    public void setReviewedTime(DateTime reviewedTime) {
        this.reviewedTime = reviewedTime;
    }
}
