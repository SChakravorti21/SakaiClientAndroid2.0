package com.example.development.sakaiclient20.models.interfaces;

import com.example.development.sakaiclient20.models.custom.Term;
import com.example.development.sakaiclient20.models.sakai.assignments.Attachment;
import com.example.development.sakaiclient20.models.sakai.assignments.DueTime;

import java.util.Date;
import java.util.List;

/**
 * Created by Development on 8/11/18.
 */

public interface IAssignment {
    Term getTerm();

    List<? extends IAttachment> getAttachments();

    String getAuthorLastModified();

    String getContext();

    String getCreator();

    Date getDueTime();

    String getGradeScale();

    String getGradeScaleMaxPoints();

    String getAssignmentId();

    String getInstructions();

    String getStatus();

    String getTitle();

    Boolean getAllowResubmission();

    String getEntityURL();

    String getEntityId();

    String getEntityTitle();

    String getEntityReference();

    String getAssignmentSitePageUrl();

}
