package com.phuongkhanh.youmetrips.services.api.models;

public class UserTrekkingPlan {
    private int id;
    private UserTrekkingPlanPlace place;
    private int numberOfComments;
    private String whenToGoMin;
    private String whenToGoMax;
    private int howLongMin;
    private int howLongMax;
    private int hotelLevel;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserTrekkingPlanPlace getPlace() {
        return place;
    }

    public void setPlace(UserTrekkingPlanPlace place) {
        this.place = place;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public String getWhenToGoMin() {
        return whenToGoMin;
    }

    public void setWhenToGoMin(String whenToGoMin) {
        this.whenToGoMin = whenToGoMin;
    }

    public String getWhenToGoMax() {
        return whenToGoMax;
    }

    public void setWhenToGoMax(String whenToGoMax) {
        this.whenToGoMax = whenToGoMax;
    }

    public int getHowLongMin() {
        return howLongMin;
    }

    public void setHowLongMin(int howLongMin) {
        this.howLongMin = howLongMin;
    }

    public int getHowLongMax() {
        return howLongMax;
    }

    public void setHowLongMax(int howLongMax) {
        this.howLongMax = howLongMax;
    }

    public int getHotelLevel() {
        return hotelLevel;
    }

    public void setHotelLevel(int hotelLevel) {
        this.hotelLevel = hotelLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
