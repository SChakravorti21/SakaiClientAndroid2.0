package com.example.development.sakaiclient20.persistence.composites;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.development.sakaiclient20.persistence.entities.CourseEntity;
import com.example.development.sakaiclient20.persistence.entities.GradeEntity;

import java.util.List;

/**
 * Created by Development on 8/11/18.
 */

public class CompositeCourse {
    @Embedded
    public final CourseEntity course;

    @Relation(parentColumn = "id", entityColumn = "courseId")
    public List<CompositeAssignment> assignments;

    @Relation(parentColumn = "id", entityColumn = "courseId")
    public List<GradeEntity> grades;

    public CompositeCourse(CourseEntity course) {
        this.course = course;
    }
}
