package com.example.development.sakaiclient20.persistence.composites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import com.example.development.sakaiclient20.models.sakai.assignments.Assignment;
import com.example.development.sakaiclient20.models.sakai.assignments.Attachment;
import com.example.development.sakaiclient20.persistence.entities.AssignmentEntity;
import com.example.development.sakaiclient20.persistence.entities.AttachmentEntity;

import java.util.List;

/**
 * Created by Development on 8/5/18.
 */


public class AssignmentWithAttachments {
    @Embedded
    public final AssignmentEntity assignment;

    @Relation(parentColumn = "id", entityColumn = "assignmentId")
    public List<Attachment> attachments;

    public AssignmentWithAttachments(AssignmentEntity assignment) {
        this.assignment = assignment;
    }
}
