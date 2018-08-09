package com.example.development.sakaiclient20.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.development.sakaiclient20.models.custom.Term;
import com.example.development.sakaiclient20.persistence.converters.TermConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Development on 8/5/18.
 */

@Entity(tableName = "assignments",
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                                    parentColumns = "id",
                                    childColumns = "courseId",
                                    onDelete = ForeignKey.CASCADE,
                                    onUpdate = ForeignKey.CASCADE))
@TypeConverters({TermConverter.class})
public class AssignmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    // Key assignment details
    private Term term;
    private String title;
    private String siteId;
    private String assignmentId;
    private String instructions;

    // Information that allows Sakai to keep track of the assignment
    private String entityURL;
    private String entityId;
    private String entityTitle;
    private String entityReference;
    private String assignmentSitePageUrl;

    // Information regarding the submission of assignment
    private String status;
    private Date dueTime;
    private Boolean allowResubmission;

    // Information about who created the assignment
    private String context;
    private String creator;
    private String authorLastModified;

    private String gradeScale;
    private String gradeScaleMaxPoints;

    public final int courseId;

    @Ignore
    private List<AttachmentEntity> attachments = new ArrayList<>();

    public AssignmentEntity(final int courseId) {
        this.courseId = courseId;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getEntityURL() {
        return entityURL;
    }

    public void setEntityURL(String entityURL) {
        this.entityURL = entityURL;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityTitle() {
        return entityTitle;
    }

    public void setEntityTitle(String entityTitle) {
        this.entityTitle = entityTitle;
    }

    public String getEntityReference() {
        return entityReference;
    }

    public void setEntityReference(String entityReference) {
        this.entityReference = entityReference;
    }

    public String getAssignmentSitePageUrl() {
        return assignmentSitePageUrl;
    }

    public void setAssignmentSitePageUrl(String assignmentSitePageUrl) {
        this.assignmentSitePageUrl = assignmentSitePageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public Boolean getAllowResubmission() {
        return allowResubmission;
    }

    public void setAllowResubmission(Boolean allowResubmission) {
        this.allowResubmission = allowResubmission;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAuthorLastModified() {
        return authorLastModified;
    }

    public void setAuthorLastModified(String authorLastModified) {
        this.authorLastModified = authorLastModified;
    }

    public String getGradeScale() {
        return gradeScale;
    }

    public void setGradeScale(String gradeScale) {
        this.gradeScale = gradeScale;
    }

    public String getGradeScaleMaxPoints() {
        return gradeScaleMaxPoints;
    }

    public void setGradeScaleMaxPoints(String gradeScaleMaxPoints) {
        this.gradeScaleMaxPoints = gradeScaleMaxPoints;
    }

    public void addAttachment(AttachmentEntity attachmentEntity) {
        this.attachments.add(attachmentEntity);
    }
}
