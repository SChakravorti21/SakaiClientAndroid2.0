package com.example.development.sakaiclient20.persistence.composites;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.development.sakaiclient20.persistence.entities.AssignmentEntity;
import com.example.development.sakaiclient20.persistence.entities.AttachmentEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by Development on 8/5/18.
 */


public class CompositeAssignment {
    @Embedded
    public final AssignmentEntity assignment;

    @Relation(parentColumn = "id", entityColumn = "assignmentId")
    public List<AttachmentEntity> attachments;

    public CompositeAssignment(AssignmentEntity assignment) {
        this.assignment = assignment;
    }

}
