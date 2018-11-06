package com.example.development.sakaiclient20.persistence.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.development.sakaiclient20.models.custom.Course;
import com.example.development.sakaiclient20.models.custom.Term;

/**
 * Created by Development on 8/5/18.
 */

@Entity(tableName = "courses")
public class CourseEntity {

    @NonNull
    @PrimaryKey
    public final String siteId;

    public String courseName;
    public String title;
    public String description;
    public Term term;
    public String siteOwner;
    public int subjectCode;

    public CourseEntity(String siteId) {
        this.siteId = siteId;
    }
}
