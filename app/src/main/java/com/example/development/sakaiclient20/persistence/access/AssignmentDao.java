package com.example.development.sakaiclient20.persistence.access;

import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.example.development.sakaiclient20.persistence.composites.AssignmentWithAttachments;
import com.example.development.sakaiclient20.persistence.entities.AssignmentEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Development on 8/5/18.
 */

public abstract class AssignmentDao implements BaseDao<AssignmentEntity> {

    @Transaction
    public void insertForSite(int courseId, AssignmentEntity... assignments) {
        deleteAllForSite(courseId);
        insert(assignments);
    }

    @Transaction
    @Query("SELECT * FROM assignments ORDER BY dueTime")
    public abstract Flowable<List<AssignmentWithAttachments>> getAllAssignments();

    @Transaction //Wrap in transaction since Room technically performs multiple transactions
    @Query("SELECT * FROM assignments WHERE siteId = :siteId")
    public abstract Flowable<List<AssignmentWithAttachments>> getAssignmentsForSite(String siteId);

    @Query("DELETE FROM assignments WHERE courseId = :siteId")
    public abstract void deleteAllForSite(int siteId);
}
