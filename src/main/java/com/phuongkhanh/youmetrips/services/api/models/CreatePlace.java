package com.phuongkhanh.youmetrips.services.api.models;

import java.util.List;

public class CreatePlace {
    private String name;
    private String location;
    private String coverImageUrl;
    private List<String> photoUrls;
    private List<String> tags;

    public CreatePlace(String name, String location, String coverImageUrl, List<String> photoUrls, List<String> tags) {
        this.name = name;
        this.location = location;
        this.coverImageUrl = coverImageUrl;
        this.photoUrls = photoUrls;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
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
