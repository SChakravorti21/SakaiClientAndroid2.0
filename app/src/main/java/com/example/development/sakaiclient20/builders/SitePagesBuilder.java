package com.example.development.sakaiclient20.builders;

import com.example.development.sakaiclient20.models.custom.SitePage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Development on 8/5/18.
 */

public class SitePagesBuilder extends AbstractBuilder<JSONArray, List<SitePage>> {

    private String assignmentSitePageUrl;

    public SitePagesBuilder(JSONArray jsonArray) {
        super(jsonArray);
    }

    @Override
    public AbstractBuilder<JSONArray, List<SitePage>> build() throws JSONException {
        result = new ArrayList<>();

        for (int index = 0; index < source.length(); index++) {
            JSONObject json = source.getJSONObject(index);
            SitePage sitePage = buildSitePage(json);
            result.add(sitePage);

            if(sitePage.getTitle().toLowerCase().contains("assignment")) {
                this.assignmentSitePageUrl = sitePage.getUrl();
            }
        }

        return this;
    }

    private SitePage buildSitePage(JSONObject json) throws JSONException {
        return new SitePage(
                json.getString("id"),
                json.getString("id"),
                json.getString("url")
        );
    }

    public String getAssignmentSitePageUrl() {
        return this.assignmentSitePageUrl != null ? this.assignmentSitePageUrl : "";
    }
}
