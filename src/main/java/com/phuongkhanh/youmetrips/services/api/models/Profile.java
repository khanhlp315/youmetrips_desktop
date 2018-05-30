package com.phuongkhanh.youmetrips.services.api.models;

import java.util.List;

;
;

public class Profile {
     private int userId;
     private String firstName;
     private String lastName;
     private String avatar;
     private String bio;
     private eNationality nationality;
     private String nationalityName;
     private String nationalityFlagUrl;
     private String birthday;
     private eGender gender;
     private String occupation;
     private String address;
     private String email;
     private String phoneNumber;
     private String identifyingMethod;
     private TrekkingPlanSet trekkingPlanSet;

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

    public eNationality getNationality() {
        return nationality;
    }

    public void setNationality(eNationality nationality) {
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

    public eGender getGender() {
        return gender;
    }

    public void setGender(eGender gender) {
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

    public TrekkingPlanSet getTrekkingPlanSet() {
        return trekkingPlanSet;
    }

    public void setTrekkingPlanSet(TrekkingPlanSet trekkingPlanSet) {
        this.trekkingPlanSet = trekkingPlanSet;
    }
}
