package com.example.development.sakaiclient20.networking.sources;

import android.content.Context;

import com.example.development.sakaiclient20.models.sakai.assignments.Assignment;
import com.example.development.sakaiclient20.models.sakai.assignments.AssignmentsResponse;
import com.example.development.sakaiclient20.networking.services.AssignmentsService;
import com.example.development.sakaiclient20.networking.services.ServiceFactory;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Development on 8/5/18.
 */

public class AssignmentsApiDataSource implements DataSource<List<Assignment>, List<Assignment>> {

    private static AssignmentsApiDataSource mInstance;
    private AssignmentsService assignmentsService;

    public static AssignmentsApiDataSource getInstance(Context context) {
        if(mInstance == null) {
            mInstance = createInstance(context);
        }
        return mInstance;
    }

    private static AssignmentsApiDataSource createInstance(Context context) {
        AssignmentsService assignmentsService =
                ServiceFactory.getService(context, AssignmentsService.class);
        return new AssignmentsApiDataSource(assignmentsService);
    }

    private AssignmentsApiDataSource(AssignmentsService assignmentsService) {
        this.assignmentsService = assignmentsService;
    }

    @Override
    public Single<List<Assignment>> getAll() {
        return assignmentsService
                .getAllAssignments()
                .map(AssignmentsResponse::getAssignments);
    }

    @Override
    public Single<List<Assignment>> getForSite(String siteId) {
        return assignmentsService
                .getSiteAssignments(siteId)
                .map(AssignmentsResponse::getAssignments);
    }
}
