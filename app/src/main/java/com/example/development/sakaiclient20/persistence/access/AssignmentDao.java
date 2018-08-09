package com.example.development.sakaiclient20.persistence.access;

import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.example.development.sakaiclient20.models.sakai.assignments.Assignment;
import com.example.development.sakaiclient20.persistence.composites.AssignmentWithAttachments;
import com.example.development.sakaiclient20.persistence.entities.AssignmentEntity;
import com.example.development.sakaiclient20.persistence.entities.AttachmentEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Development on 8/5/18.
 */

public abstract class AssignmentDao implements BaseDao<AssignmentEntity> {

    @Query("SELECT * FROM assignments WHERE siteId = :siteId")
    public abstract List<AssignmentWithAttachments> getAllAssignmentsForSite(String siteId);

    @Query("SELECT * FROM assignments WHERE siteId = :siteId")
    public abstract Flowable<AssignmentEntity> getAssignmentsForSite(String siteId);

    @Query("SELECT * FROM assignments WHERE assignmentId = :assignmentId")
    public abstract Flowable<AttachmentEntity> getAttachmentsForAssignment(String assignmentId);

}
