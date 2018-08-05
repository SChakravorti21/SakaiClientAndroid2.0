package com.example.development.sakaiclient20.builders.courses;

import com.example.development.sakaiclient20.builders.Builder;
import com.example.development.sakaiclient20.models.custom.Course;
import com.example.development.sakaiclient20.models.custom.SitePage;
import com.example.development.sakaiclient20.models.custom.Term;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Development on 8/5/18.
 */

public class CourseBuilder extends Builder<JSONObject, Course> {

    CourseBuilder(JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public CourseBuilder build() throws JSONException {
        result = new Course();

        String id = source.getString("id");
        result.setId(id);

        String desc = source.getString("description");
        result.setDescription(desc);

        String title = source.getString("title");
        result.setTitle(title);

        JSONObject props = source.getJSONObject("props");
        try {
            String termEID = props.getString("term_eid");
            Term courseTerm = new Term(termEID);
            result.setTerm(courseTerm);
        }
        catch(JSONException e) {
            Term courseTerm = new Term("0000:0");
            result.setTerm(courseTerm);
        }

        JSONObject siteOwner = source.getJSONObject("siteOwner");
        String ownerName = siteOwner.getString("userDisplayName");
        result.setSiteOwner(ownerName);


        String providerGroupId = source.getString("providerGroupId");
        if (!providerGroupId.equals("null")) {

            providerGroupId = providerGroupId.replace("+", "_delim_");

            String courseCode = providerGroupId.split("_delim_")[0];
            String subjectCode = courseCode.split(":")[3];
            result.setSubjectCode(Integer.parseInt(subjectCode));

        }

        ArrayList<SitePage> sitePages = new ArrayList<>();
        JSONArray sitePagesObj = source.getJSONArray("sitePages");
        for (int j = 0; j < sitePagesObj.length(); j++) {
            JSONObject pageObj = sitePagesObj.getJSONObject(j);
            SitePage sitePage = new SitePage(pageObj);
            sitePages.add(sitePage);

            if(sitePage.getTitle().toLowerCase().contains("assignment")) {
                result.setAssignmentSitePageUrl(sitePage.getUrl());
            }
        }

        result.setSitePages(sitePages);

        return this;
    }
}
