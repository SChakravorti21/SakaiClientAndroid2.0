package com.example.development.sakaiclient20.models.sakai.gradebook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Grade {

    @SerializedName("grade")
    @Expose
    private String grade;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("points")
    @Expose
    private Double points;

    public String getGrade() {
        return grade;
    }

    public String getItemName() {
        return itemName;
    }

    public Double getPoints() {
        return points;
    }

}
