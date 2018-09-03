package com.example.development.sakaiclient20.persistence.access;

import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.example.development.sakaiclient20.persistence.composites.CompositeAssignment;
import com.example.development.sakaiclient20.persistence.entities.Assignment;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Development on 8/5/18.
 */

public abstract class AssignmentDao implements BaseDao<Assignment> {

    @Transaction
    public void insertForSite(int courseId, Assignment... assignments) {
        deleteAllForSite(courseId);
        insert(assignments);
    }

    @Transaction
    @Query("SELECT * FROM Assignment ORDER BY dueTime")
    public abstract Flowable<List<CompositeAssignment>> getAllAssignments();

    @Transaction //Wrap in transaction since Room technically performs multiple transactions
    @Query("SELECT * FROM Assignment WHERE siteId = :siteId")
    public abstract Flowable<List<CompositeAssignment>> getAssignmentsForSite(String siteId);

    @Query("DELETE FROM Assignment WHERE siteId = :siteId")
    public abstract void deleteAllForSite(int siteId);
}
