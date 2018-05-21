package ru.kpfu.itis.abiturkfu.model.entities;

import com.google.gson.annotations.SerializedName;

public class AboutFacultets {
    private int id;
    @SerializedName("facultet_id")
    private int facultetId;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacultetId() {
        return facultetId;
    }

    public void setFacultetId(int facultetId) {
        this.facultetId = facultetId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
