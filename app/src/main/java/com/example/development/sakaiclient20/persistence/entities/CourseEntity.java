package com.example.development.sakaiclient20.persistence.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

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
    public final String term;
    public final String siteOwner;
    public final String assignmentSitePageUrl;
    public final int subjectCode;

    public CourseEntity(
            String siteId,
            String courseName,
            String title,
            String description,
            String term,
            String siteOwner,
            String assignmentSitePageUrl,
            int subjectCode
    ) {
        this.siteId = siteId;
        this.courseName = courseName;
        this.title = title;
        this.description = description;
        this.term = term;
        this.siteOwner = siteOwner;
        this.assignmentSitePageUrl = assignmentSitePageUrl;
        this.subjectCode = subjectCode;
    }
}
