package com.example.development.sakaiclient20.persistence.composites;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.development.sakaiclient20.models.custom.Term;
import com.example.development.sakaiclient20.models.interfaces.IAssignment;
import com.example.development.sakaiclient20.models.interfaces.IAttachment;
import com.example.development.sakaiclient20.persistence.entities.AssignmentEntity;
import com.example.development.sakaiclient20.persistence.entities.AttachmentEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by Development on 8/5/18.
 */


public class AssignmentWithAttachments implements IAssignment {
    @Embedded
    public final AssignmentEntity assignment;

    @Relation(parentColumn = "id", entityColumn = "assignmentId")
    public List<AttachmentEntity> attachments;

    public AssignmentWithAttachments(AssignmentEntity assignment) {
        this.assignment = assignment;
    }

    public Term getTerm() {
        return assignment.getTerm();
    }

    public String getTitle() {
        return assignment.getTitle();
    }

    public String getSiteId() {
        return assignment.getSiteId();
    }

    public String getAssignmentId() {
        return assignment.getAssignmentId();
    }

    public String getInstructions() {
        return assignment.getInstructions();
    }

    public String getEntityURL() {
        return assignment.getEntityURL();
    }

    public String getEntityId() {
        return assignment.getEntityId();
    }

    public String getEntityTitle() {
        return assignment.getEntityTitle();
    }

    public String getEntityReference() {
        return assignment.getEntityReference();
    }

    public String getAssignmentSitePageUrl() {
        return assignment.getAssignmentSitePageUrl();
    }

    public String getStatus() {
        return assignment.getStatus();
    }

    public Date getDueTime() {
        return assignment.getDueTime();
    }

    public Boolean getAllowResubmission() {
        return assignment.getAllowResubmission();
    }

    public String getContext() {
        return assignment.getContext();
    }

    public String getCreator() {
        return assignment.getCreator();
    }

    public String getAuthorLastModified() {
        return assignment.getAuthorLastModified();
    }

    public String getGradeScale() {
        return assignment.getGradeScale();
    }

    public String getGradeScaleMaxPoints() {
        return assignment.getGradeScaleMaxPoints();
    }

    public List<? extends IAttachment> getAttachments() {
        return this.attachments;
    }
}
