package com.phuongkhanh.youmetrips.services.api.models;

public class RelevantPlan {
    private int userId;
    private String userFirstName;
    private String userLastName;
    private String userAvatarUrl;
    private String userOccupation;
    private String userNationalityFlagUrl;
    private int planId;
    private RelevantPlanPlace place;
    private int numberOfComments;
    private DateTime whenToGoMin;
    private DateTime whenToGoMax;
    private int howLongMin;
    private int howLongMax;
    private int hotelLevel;
    private String description;


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

    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }

    public String getUserNationalityFlagUrl() {
        return userNationalityFlagUrl;
    }

    public void setUserNationalityFlagUrl(String userNationalityFlagUrl) {
        this.userNationalityFlagUrl = userNationalityFlagUrl;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public RelevantPlanPlace getPlace() {
        return place;
    }

    public void setPlace(RelevantPlanPlace place) {
        this.place = place;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public DateTime getWhenToGoMin() {
        return whenToGoMin;
    }

    public void setWhenToGoMin(DateTime whenToGoMin) {
        this.whenToGoMin = whenToGoMin;
    }

    public DateTime getWhenToGoMax() {
        return whenToGoMax;
    }

    public void setWhenToGoMax(DateTime whenToGoMax) {
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
