package com.example.development.sakaiclient20.models.custom;

import com.example.development.sakaiclient20.models.sakai.gradebook.Grade;
import com.example.development.sakaiclient20.persistence.entities.Assignment;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {


    private String id;
    private String title;
    private String description;
    private Term term;
    private List<SitePage> sitePages;
    private String siteOwner;
    private int subjectCode;
    private List<Grade> grades;
    private List<Assignment> assignments;
    private String assignmentSitePageUrl;

    public Course() {
        this.assignments = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(this);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        Course course = (Course) in.readObject();
        this.assignments = course.assignments;
        this.title = course.title;
        this.term = course.term;
    }


    @Override
    public String toString() {
        String ret = (this.title + " : " + this.term.toString() + "     Sites:   ");
        for (SitePage s : this.sitePages) {
            ret += s.toString() + ";  ";
        }

        return ret;

    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Term getTerm() {
        return term;
    }

    public List<SitePage> getSitePages() {
        return sitePages;
    }

    public String getSiteOwner() {
        return siteOwner;
    }

    public int getSubjectCode() {
        return subjectCode;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public void setSitePages(List<SitePage> sitePages) {
        this.sitePages = sitePages;
    }

    public void setSiteOwner(String siteOwner) {
        this.siteOwner = siteOwner;
    }

    public void setSubjectCode(int subjectCode) {
        this.subjectCode = subjectCode;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> gradebookObjectList) {
        this.grades = gradebookObjectList;
    }

    public List<Assignment> getAssignments() {
        return this.assignments;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void clearAssignments() {
        assignments.clear();
    }

    public int getNumAssignments() {
        return (this.assignments != null) ? this.assignments.size() : 0;
    }

    public String getAssignmentSitePageUrl() {
        return assignmentSitePageUrl;
    }

    public void setAssignmentSitePageUrl(String assignmentSitePageUrl) {
        this.assignmentSitePageUrl = assignmentSitePageUrl;
    }

}
