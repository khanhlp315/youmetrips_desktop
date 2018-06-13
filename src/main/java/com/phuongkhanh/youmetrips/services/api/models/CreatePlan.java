package com.phuongkhanh.youmetrips.services.api.models;

public class CreatePlan {
    private int placeId;
    private String whenToGoMin;
    private String whenToGoMax;
    private int howLongMin;
    private int howLongMax;
    private int hotelLevel;
    private String description;

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getHotelLevel() {
        return hotelLevel;
    }

    public void setHotelLevel(int hotelLevel) {
        this.hotelLevel = hotelLevel;
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

    public String getWhenToGoMax() {
        return whenToGoMax;
    }

    public void setWhenToGoMax(String whenToGoMax) {
        this.whenToGoMax = whenToGoMax;
    }

    public String getWhenToGoMin() {
        return whenToGoMin;
    }

    public void setWhenToGoMin(String whenToGoMin) {
        this.whenToGoMin = whenToGoMin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
