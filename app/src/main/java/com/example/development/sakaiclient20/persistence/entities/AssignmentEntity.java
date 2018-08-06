package com.example.development.sakaiclient20.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.development.sakaiclient20.models.custom.Term;
import com.example.development.sakaiclient20.persistence.converters.TermConverter;

import java.util.Date;

/**
 * Created by Development on 8/5/18.
 */

@Entity(tableName = "assignments",
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                                    parentColumns = "courseId",
                                    childColumns = "assignmentId",
                                    onDelete = ForeignKey.CASCADE,
                                    onUpdate = ForeignKey.CASCADE))
@TypeConverters({TermConverter.class})
public class AssignmentEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "assignmentId")
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

    //private List<Attachment> attachments = new ArrayList<Attachment>();
}
