package com.example.development.sakaiclient20.networking.deserializers.builders.courses;

import com.example.development.sakaiclient20.models.custom.Course;
import com.example.development.sakaiclient20.models.custom.SitePage;
import com.example.development.sakaiclient20.models.custom.Term;
import com.example.development.sakaiclient20.networking.deserializers.builders.AbstractBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by Development on 8/5/18.
 */

public class CourseBuilder extends AbstractBuilder<JsonObject, Course> {

    public CourseBuilder(JsonObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public CourseBuilder build() {
        result = new Course();

        result.setTerm(parseTerm());
        result.setSubjectCode(parseSubjectCode());

        result.setId(source.get("id").getAsString());
        result.setTitle(source.get("title").getAsString());
        result.setDescription(source.get("description").getAsString());
        result.setSiteOwner(
                source.getAsJsonObject("siteOwner")
                    .get("userDisplayName")
                    .getAsString()
        );

        JsonArray rawSitePages = source.getAsJsonArray("sitePages");
        SitePagesBuilder builder = new SitePagesBuilder(rawSitePages);
        List<SitePage> sitePages = builder.build().getResult();
        result.setSitePages(sitePages);
        result.setAssignmentSitePageUrl(builder.getAssignmentSitePageUrl());

        return this;
    }

    private Term parseTerm() {
        JsonObject props = source.getAsJsonObject("props");
        JsonElement termEidElement = props.get("term_eid");

        // It is possible that even if props is defined, term_eid
        // is not provided, in which case the element itself will
        // be null (instead of holding a null value and being JsonNull)
        if(termEidElement == null)
            return new Term("0000:0");

        String termEid = termEidElement.getAsString();
        return new Term(termEid);
    }

    private int parseSubjectCode() {
        JsonElement providerGroupIdElement = source.get("providerGroupId");
        if(providerGroupIdElement instanceof JsonNull)
            return 0;

        String providerGroupId = providerGroupIdElement.getAsString();
        providerGroupId = providerGroupId.replace("+", "_delim_");
        String courseCode = providerGroupId.split("_delim_")[0];
        String subjectCode = courseCode.split(":")[3];
        return Integer.parseInt(subjectCode);
    }
}
