package com.example.development.sakaiclient20.models.sakai.assignments;

import com.example.development.sakaiclient20.models.custom.Term;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Assignment implements Serializable {

    @SerializedName("attachments")
    public List<Attachment> attachments = new ArrayList<Attachment>();

    @SerializedName("authorLastModified")
    public String authorLastModified;

    @SerializedName("closeTimeString")
    public String closeTimeString;

    @SerializedName("context")
    public String context;

    @SerializedName("creator")
    public String creator;

    @SerializedName("dueTime")
    public DueTime dueTime;

    @SerializedName("dueTimeString")
    public String dueTimeString;

    @SerializedName("gradeScale")
    public String gradeScale;

    @SerializedName("gradeScaleMaxPoints")
    public String gradeScaleMaxPoints;

    @SerializedName("id")
    public String assignmentId;

    @SerializedName("instructions")
    public String instructions;

    @SerializedName("modelAnswerText")
    public Object modelAnswerText;

    @SerializedName("status")
    public String status;

    @SerializedName("title")
    public String title;

    @SerializedName("allowResubmission")
    public Boolean allowResubmission;

    @SerializedName("entityURL")
    public String entityURL;

    @SerializedName("entityId")
    public String entityId;

    @SerializedName("entityTitle")
    public String entityTitle;

    @SerializedName("entityReference")
    public String entityReference;

    // Term does not come in the response, but it is used internally
    // for sorting by date

    @SerializedName("term")
    public Term term;

    @SerializedName("assignmentSitePageUrl")
    public String assignmentSitePageUrl;

    public final static long serialVersionUID = 835944991348229740L;

    public Date getDueTime() {
        return new Date(dueTime.getTime());
    }

    public void setDueTime(Date time) {
        DueTime dueTime = new DueTime();
        dueTime.setTime(time.getTime());
        dueTime.setDisplay(time.toString());

        this.dueTime = dueTime;
    }
}
