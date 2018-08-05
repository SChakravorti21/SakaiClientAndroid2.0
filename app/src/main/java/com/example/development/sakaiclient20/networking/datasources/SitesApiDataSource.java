package com.example.development.sakaiclient20.networking.datasources;

import android.content.Context;

import com.example.development.sakaiclient20.builders.courses.CoursesBuilder;
import com.example.development.sakaiclient20.models.custom.Course;
import com.example.development.sakaiclient20.networking.services.ServiceFactory;
import com.example.development.sakaiclient20.networking.services.SitesService;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Shoumyo Chakravorti on 8/5/18.
 */

public class SitesApiDataSource implements DataSource<List<Course>, Course> {

    private static SitesApiDataSource mInstance;
    private SitesService sitesService;

    public SitesApiDataSource getInstance(Context context) {
        if(mInstance == null) {
            mInstance = createInstance(context);
        }
        return mInstance;
    }

    private SitesApiDataSource(SitesService sitesService) {
        this.sitesService = sitesService;
    }

    private SitesApiDataSource createInstance(Context context) {
        SitesService sitesService = ServiceFactory.getService(context, SitesService.class);
        return new SitesApiDataSource(sitesService);
    }

    @Override
    public Observable<List<Course>> getAll() {
        return sitesService
            .getAllSites()
            .map(responseBody -> new CoursesBuilder(responseBody).build().getResult())
            .toObservable();
    }

    @Override
    public Observable<Course> getForSite(String siteId) {
        return null;
    }
}
