package com.example.development.sakaiclient20.repositories;

import android.support.annotation.NonNull;

import com.example.development.sakaiclient20.models.Term;
import com.example.development.sakaiclient20.models.sakai.courses.CoursesResponse;
import com.example.development.sakaiclient20.networking.services.CoursesService;
import com.example.development.sakaiclient20.persistence.access.CourseDao;
import com.example.development.sakaiclient20.persistence.composites.CompositeAssignment;
import com.example.development.sakaiclient20.persistence.composites.CompositeCourse;
import com.example.development.sakaiclient20.persistence.entities.Assignment;
import com.example.development.sakaiclient20.persistence.entities.Course;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Single;

public class CourseRepository {

    private CourseDao courseDao;
    private CoursesService coursesService;

    public CourseRepository(CourseDao courseDao, CoursesService coursesService) {
        this.courseDao = courseDao;
        this.coursesService = coursesService;
    }

    public Single<List<List<Course>>> getCoursesSortedByTerm(boolean refresh) {
        if(refresh) {
            return coursesService.getAllSites()
                    .map(CoursesResponse::getCourses)
                    .map(this::sortCoursesByTerm);
        } else {
            return courseDao.getAllCourses()
                    .toObservable()
                    .flatMapIterable(courses -> courses)
                    .map(this::flattenCompositeToEntity)
                    .toList()
                    .map(this::sortCoursesByTerm);
        }
    }

    private Course flattenCompositeToEntity(CompositeCourse compositeCourse) {
        Course entity = compositeCourse.course;
        entity.sitePages = compositeCourse.sitePages;
        entity.grades = compositeCourse.grades;

        List<Assignment> assignmentEntities = new ArrayList<>();
        for(CompositeAssignment composite : compositeCourse.assignments) {
            Assignment assignment = composite.assignment;
            assignment.attachments = composite.attachments;
            assignmentEntities.add(assignment);
        }

        entity.assignments = assignmentEntities;
        return entity;
    }

    private List<List<Course>> sortCoursesByTerm(List<Course> courses) {
        TreeMap<Term, List<Course>> coursesByTerm = new TreeMap<>();

        for(Course course : courses) {
            Term term = course.term;
            if(coursesByTerm.containsKey(term)) {
                coursesByTerm.get(term).add(course);
            } else {
                List<Course> coursesForTerm = new ArrayList<>();
                coursesForTerm.add(course);
                coursesByTerm.put(term, coursesForTerm);
            }
        }

        List<List<Course>> coursesSortedByTerm = new ArrayList<>(coursesByTerm.size());
        // We need to go through the keySet in descending order since the
        // more recent terms have larger values (year + starting month)
        for(Term term : coursesByTerm.descendingKeySet()) {
            coursesSortedByTerm.add(coursesByTerm.get(term));
        }

        return coursesSortedByTerm;
    }

}
