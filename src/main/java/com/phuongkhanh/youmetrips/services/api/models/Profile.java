package com.phuongkhanh.youmetrips.services.api.models;

import java.util.List;

public class Profile {
     private int userId;
     private String firstName;
     private String lastName;
     private String avatar;
     private String bio;
     private String nationality;
     private String nationalityName;
     private String nationalityFlagUrl;
     private String birthday;
     private String gender;
     private String occupation;
     private String address;
     private String email;
     private String phoneNumber;
     private String identifyingMethod;
     private List<UserTrekkingPlan> trekkingPlanSet;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    public String getNationalityFlagUrl() {
        return nationalityFlagUrl;
    }

    public void setNationalityFlagUrl(String nationalityFlagUrl) {
        this.nationalityFlagUrl = nationalityFlagUrl;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentifyingMethod() {
        return identifyingMethod;
    }

    public void setIdentifyingMethod(String identifyingMethod) {
        this.identifyingMethod = identifyingMethod;
    }

    public List<UserTrekkingPlan> getTrekkingPlanSet() {
        return trekkingPlanSet;
    }

    public void setTrekkingPlanSet(List<UserTrekkingPlan> trekkingPlanSet) {
        this.trekkingPlanSet = trekkingPlanSet;
    }
}

class UserTrekkingPlan{
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

class UserTrekkingPlanPlace{
    private int id;
    private String name;
    private String coverImageUrl;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}