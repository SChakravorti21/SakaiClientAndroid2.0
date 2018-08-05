package com.example.development.sakaiclient20.builders.courses;

import com.example.development.sakaiclient20.builders.Builder;
import com.example.development.sakaiclient20.models.custom.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by Development on 8/5/18.
 */

public class CoursesBuilder extends Builder<ResponseBody, List<Course>> {

    public CoursesBuilder(ResponseBody apiResponse) {
        super(apiResponse);
    }

    @Override
    public CoursesBuilder build() throws IOException, JSONException {
        this.result = new ArrayList<>();

        JSONObject obj = new JSONObject(this.source.string());
        JSONArray courses = obj.getJSONArray("site_collection");

        for (int i = 0; i < courses.length(); i++) {

            JSONObject courseJSON = courses.getJSONObject(i);
            Course course = new CourseBuilder(courseJSON).build().getResult();
            this.result.add(course);

            //mapSiteIdToCourse.put(course.getId(), course);
        }

        return this;
    }

}
