package ru.kpfu.itis.abiturkfu.model.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

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
    // TODO: 05.05.18 add about_facultets


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facility facility = (Facility) o;
        return id == facility.id &&
                Double.compare(facility.middle_score, middle_score) == 0 &&
                Objects.equals(name, facility.name) &&
                Objects.equals(image_link, facility.image_link) &&
                Objects.equals(city, facility.city) &&
                Objects.equals(address, facility.address) &&
                Objects.equals(email, facility.email) &&
                Objects.equals(phone, facility.phone) &&
                Objects.equals(website, facility.website);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, middle_score, image_link, city, address, email, phone, website);
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
