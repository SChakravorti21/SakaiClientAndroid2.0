package com.example.development.sakaiclient20.builders.courses;

import com.example.development.sakaiclient20.builders.AbstractBuilder;
import com.example.development.sakaiclient20.builders.SitePagesBuilder;
import com.example.development.sakaiclient20.models.custom.Course;
import com.example.development.sakaiclient20.models.custom.SitePage;
import com.example.development.sakaiclient20.models.custom.Term;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Development on 8/5/18.
 */

public class CourseBuilder extends AbstractBuilder<JSONObject, Course> {

    public CourseBuilder(JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public CourseBuilder build() throws JSONException {
        result = new Course();

        result.setTerm(parseTerm());
        result.setSubjectCode(parseSubjectCode());

        result.setId(source.getString("id"));
        result.setTitle(source.getString("title"));
        result.setDescription(source.getString("description"));
        result.setSiteOwner(source.getJSONObject("siteOwner").getString("userDisplayName"));

        List<SitePage> sitePages = new ArrayList<>();
        SitePagesBuilder builder = new SitePagesBuilder(source.getJSONArray("sitePages"));
        try {
            sitePages = builder.build().getResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            result.setSitePages(sitePages);
            result.setAssignmentSitePageUrl(builder.getAssignmentSitePageUrl());
        }

        return this;
    }

    private Term parseTerm() {
        Term courseTerm;
        try {
            JSONObject props = source.getJSONObject("props");
            String termEid = props.getString("term_eid");
            courseTerm = new Term(termEid);
        } catch(JSONException e) {
            courseTerm = new Term("0000:0");
        }

        return courseTerm;
    }

    private int parseSubjectCode() throws JSONException {

        String providerGroupId = source.getString("providerGroupId");

        if (!providerGroupId.equals("null")) {
            providerGroupId = providerGroupId.replace("+", "_delim_");
            String courseCode = providerGroupId.split("_delim_")[0];
            String subjectCode = courseCode.split(":")[3];

            return Integer.parseInt(subjectCode);
        }

        return 0;
    }
}
