package com.example.development.sakaiclient20.networking.sources;

import android.content.Context;

import com.example.development.sakaiclient20.builders.courses.CourseBuilder;
import com.example.development.sakaiclient20.builders.courses.CoursesBuilder;
import com.example.development.sakaiclient20.models.custom.Course;
import com.example.development.sakaiclient20.networking.services.ServiceFactory;
import com.example.development.sakaiclient20.networking.services.SitesService;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Shoumyo Chakravorti on 8/5/18.
 */

public class SitesApiDataSource implements DataSource<List<Course>, Course> {

    private static SitesApiDataSource mInstance;
    private SitesService sitesService;

    public static SitesApiDataSource getInstance(Context context) {
        if(mInstance == null) {
            mInstance = createInstance(context);
        }
        return mInstance;
    }

    private static SitesApiDataSource createInstance(Context context) {
        SitesService sitesService = ServiceFactory.getService(context, SitesService.class);
        return new SitesApiDataSource(sitesService);
    }

    private SitesApiDataSource(SitesService sitesService) {
        this.sitesService = sitesService;
    }

    @Override
    public Single<List<Course>> getAll() {
        return sitesService
            .getAllSites()
            .map(responseBody -> new CoursesBuilder(responseBody).build().getResult());
    }

    @Override
    public Single<Course> getForSite(String siteId) {
        return sitesService
                .getSingleSite(siteId)
                .map(responseBody -> {
                    JSONObject json = new JSONObject(responseBody.string());
                    return new CourseBuilder(json).build().getResult();
                });
    }
}
