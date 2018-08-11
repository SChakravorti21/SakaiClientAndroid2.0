package com.example.development.sakaiclient20.persistence.access;

import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.example.development.sakaiclient20.models.custom.Term;
import com.example.development.sakaiclient20.persistence.composites.CompositeCourse;
import com.example.development.sakaiclient20.persistence.entities.CourseEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Development on 8/11/18.
 */

public abstract class CourseDao implements BaseDao<CourseEntity> {

    @Transaction
    @Query("SELECT * FROM courses")
    public abstract Flowable<List<CompositeCourse>> getAllCourses();

    @Query("SELECT term FROM courses where siteId = :siteId")
    public abstract Term getTermForCourse(String siteId);
}
