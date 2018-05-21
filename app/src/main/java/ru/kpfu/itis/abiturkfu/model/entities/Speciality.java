
package ru.kpfu.itis.abiturkfu.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Speciality {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("facultet_id")
    @Expose
    private Integer facultetId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("contract_place")
    @Expose
    private Integer contractPlace;

    @SerializedName("budget_place")
    @Expose
    private Integer budgetPlace;

    @SerializedName("subjects")
    @Expose
    private List<Subject> subjects = null;

    @SerializedName("education_types")
    @Expose
    private List<EducationType> educationTypes = null;

    @SerializedName("specialty_education_types")
    @Expose
    private List<SpecialtyEducationType> specialtyEducationTypes;

    @SerializedName("education_forms")
    @Expose
    private List<EducationForm> educationForms = null;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacultetId() {
        return facultetId;
    }

    public void setFacultetId(Integer facultetId) {
        this.facultetId = facultetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getContractPlace() {
        return contractPlace;
    }

    public void setContractPlace(Integer contractPlace) {
        this.contractPlace = contractPlace;
    }

    public Integer getBudgetPlace() {
        return budgetPlace;
    }

    public void setBudgetPlace(Integer budgetPlace) {
        this.budgetPlace = budgetPlace;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<EducationType> getEducationTypes() {
        return educationTypes;
    }

    public void setEducationTypes(List<EducationType> educationTypes) {
        this.educationTypes = educationTypes;
    }

    public List<SpecialtyEducationType> getSpecialtyEducationTypes() {
        return specialtyEducationTypes;
    }

    public void setSpecialtyEducationTypes(List<SpecialtyEducationType> specialtyEducationTypes) {
        this.specialtyEducationTypes = specialtyEducationTypes;
    }

    public List<EducationForm> getEducationForms() {
        return educationForms;
    }

    public void setEducationForms(List<EducationForm> educationForms) {
        this.educationForms = educationForms;
    }

}
