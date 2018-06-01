package com.phuongkhanh.youmetrips.services.api.models;

import java.util.List;

public class PlanDetailsPlace {
    private int id;
    private String name;
    private String coverImageUrl;
    private List<String> tags;

    PlanDetailsPlace(int id, String name, String coverImageUrl, List<String> tags) {
        this.id = id;
        this.name = name;
        this.coverImageUrl = coverImageUrl;
        this.tags = tags;
    }


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
