package com.example.development.sakaiclient20.persistence.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.development.sakaiclient20.models.custom.Term;

/**
 * Created by Development on 8/5/18.
 */

@Entity(tableName = "courses")
public class CourseEntity {

    @PrimaryKey
    public final String siteId;

    public final String courseName;
    public final String title;
    public final String description;
    public final Term term;
    public final String siteOwner;
    public final int subjectCode;

    public CourseEntity(
            String siteId,
            String courseName,
            String title,
            String description,
            Term term,
            String siteOwner,
            int subjectCode
    ) {
        this.siteId = siteId;
        this.courseName = courseName;
        this.title = title;
        this.description = description;
        this.term = term;
        this.siteOwner = siteOwner;
        this.subjectCode = subjectCode;
    }
}
