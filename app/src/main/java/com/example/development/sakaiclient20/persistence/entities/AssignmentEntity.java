package com.example.development.sakaiclient20.persistence.entities;

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
                                    parentColumns = "siteId",
                                    childColumns = "context",
                                    onDelete = ForeignKey.CASCADE,
                                    onUpdate = ForeignKey.CASCADE))
@TypeConverters({TermConverter.class})
public class AssignmentEntity {

    @PrimaryKey
    public String assignmentId;

    // Key assignment details
    public Term term;
    public String title;
    public String context;
    public String instructions;

    // Information that allows Sakai to keep track of the assignment
    public String entityURL;
    public String entityId;
    public String entityTitle;
    public String entityReference;

    // Information regarding the submission of assignment
    public String status;
    public Date dueTime;
    public Boolean allowResubmission;

    // Information about who created the assignment
    public String creator;
    public String authorLastModified;

    // Information regarding the grading scale
    public String gradeScale;
    public String gradeScaleMaxPoints;

    @Ignore
    public List<AttachmentEntity> attachments = new ArrayList<>();

    public AssignmentEntity() { }

}
