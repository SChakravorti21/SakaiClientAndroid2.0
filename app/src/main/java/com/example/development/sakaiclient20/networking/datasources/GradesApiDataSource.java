package com.example.development.sakaiclient20.networking.datasources;

import android.content.Context;

import com.example.development.sakaiclient20.models.sakai.gradebook.Grade;
import com.example.development.sakaiclient20.models.sakai.gradebook.GradeCollection;
import com.example.development.sakaiclient20.models.sakai.gradebook.GradesResponse;
import com.example.development.sakaiclient20.networking.services.GradesService;
import com.example.development.sakaiclient20.networking.services.ServiceFactory;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Development on 8/5/18.
 */

public class GradesApiDataSource implements DataSource<List<GradeCollection>, List<Grade>> {

    private static GradesApiDataSource mInstance;
    private GradesService gradesService;

    public static GradesApiDataSource getInstance(Context context) {
        if(mInstance == null) {
            mInstance = createInstance(context);
        }
        return mInstance;
    }

    private static GradesApiDataSource createInstance(Context context) {
        GradesService assignmentsService = ServiceFactory.getService(context, GradesService.class);
        return new GradesApiDataSource(assignmentsService);
    }

    private GradesApiDataSource(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @Override
    public Observable<List<GradeCollection>> getAll() {
        return gradesService
                .getAllGrades()
                .map(GradesResponse::getGradeCollection);
    }

    @Override
    public Observable<List<Grade>> getForSite(String siteId) {
        return gradesService
                .getGradeForSite(siteId)
                .map(GradeCollection::getAssignments);
    }
}
