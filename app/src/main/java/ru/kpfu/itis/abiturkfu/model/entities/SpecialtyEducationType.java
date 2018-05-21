
package ru.kpfu.itis.abiturkfu.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpecialtyEducationType implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("specialty_id")
    @Expose
    private Integer specialityId;
    @SerializedName("education_type_id")
    @Expose
    private Integer educationTypeId;

    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("place")
    @Expose
    private Integer place;

    public SpecialtyEducationType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Integer specialityId) {
        this.specialityId = specialityId;
    }

    public Integer getEducationTypeId() {
        return educationTypeId;
    }

    public void setEducationTypeId(Integer educationTypeId) {
        this.educationTypeId = educationTypeId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

}
