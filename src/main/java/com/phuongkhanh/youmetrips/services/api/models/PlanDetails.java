package com.phuongkhanh.youmetrips.services.api.models;

public class PlanDetails {
    private int id;
    private DateTime whenToGoMin;
    private DateTime whenToGoMax;
    private int howLongMin;
    private int howLongMax;
    private int hotelLevel;
    private String description;
    private int userId;
    private String userFirstName;
    private String userLastName;
    private String userAvatarUrl;
    private String userOccupation;

    private PlanDetailsPlace place;

    public PlanDetails(
            int id,
            DateTime whenToGoMin,
            DateTime whenToGoMax,
            int howLongMin,
            int howLongMax,
            int hotelLevel,
            String description,
            PlanDetailsPlace place,
            int userId,
            String userFirstName,
            String userLastName,
            String userAvatarUrl,
            String userOccupation) {
        this.id = id;
        this.whenToGoMin = whenToGoMin;
        this.whenToGoMax = whenToGoMax;
        this.howLongMin = howLongMin;
        this.howLongMax = howLongMax;
        this.hotelLevel = hotelLevel;
        this.description = description;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userAvatarUrl = userAvatarUrl;
        this.userOccupation = userOccupation;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public PlanDetailsPlace getPlace() {
        return place;
    }

    public void setPlace(PlanDetailsPlace place) {
        this.place = place;
    }
}

