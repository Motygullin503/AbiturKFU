package ru.kpfu.itis.abiturkfu.model.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * This class store main information about facility and uses in {@link ru.kpfu.itis.abiturkfu.model.database_module.AbiturientDatabase}
 */
@Entity(tableName = "facilities")
public class Facility implements Serializable {
    @PrimaryKey
    private int id;
    private String name;
    private double middle_score;
    private String image_link;
    private String city;
    private String address;
    private String email;
    private String phone;
    private String website;
    private String description;

    @Ignore
    @SerializedName("about_facultets")
    private List<AboutFacultets> aboutFacultets;

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

    public double getMiddle_score() {
        return middle_score;
    }

    public void setMiddle_score(double middle_score) {
        this.middle_score = middle_score;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        if (description != null) {
            return description;
        }
        if (aboutFacultets != null && aboutFacultets.get(0) != null) {
            return aboutFacultets.get(0).getDescription();
        }
        return null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AboutFacultets> getAboutFacultets() {
        return aboutFacultets;
    }

    public void setAboutFacultets(List<AboutFacultets> aboutFacultets) {
        this.aboutFacultets = aboutFacultets;
    }
}
