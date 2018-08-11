package com.example.development.sakaiclient20.repositories;

import android.annotation.SuppressLint;

import com.example.development.sakaiclient20.models.interfaces.IAssignment;
import com.example.development.sakaiclient20.models.interfaces.IAttachment;
import com.example.development.sakaiclient20.models.sakai.assignments.Assignment;
import com.example.development.sakaiclient20.models.sakai.assignments.AssignmentsResponse;
import com.example.development.sakaiclient20.networking.services.AssignmentsService;
import com.example.development.sakaiclient20.persistence.access.AssignmentDao;
import com.example.development.sakaiclient20.persistence.composites.AssignmentWithAttachments;
import com.example.development.sakaiclient20.persistence.entities.AssignmentEntity;
import com.example.development.sakaiclient20.persistence.entities.AttachmentEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Development on 8/8/18.
 */

public class AssignmentRepository {

    private AssignmentDao assignmentDao;
    private AssignmentsService assignmentsService;

    public AssignmentRepository(AssignmentDao dao, AssignmentsService service) {
        this.assignmentDao = dao;
        this.assignmentsService = service;
    }

    public Single<List<? extends IAssignment>> getAssignmentsForSite(String siteId, boolean refresh) {
        if(refresh) {
            return getAllAssignmentForSiteApi(siteId)
                    .map(assignments -> {
                        return (List<IAssignment>)(List)assignments;
                    });
        } else {
            return getAssignmentsForSiteDb(siteId)
                    .map(assignmentsWithAttachments -> {
                        return (List<IAssignment>)(List) assignmentsWithAttachments;
                    });
        }
    }

    private Single<List<AssignmentWithAttachments>> getAssignmentsForSiteDb(String siteId) {
        return assignmentDao
                .getAssignmentsForSite(siteId)
                .firstOrError();
    }

    private Single<List<Assignment>> getAllAssignmentForSiteApi(String siteId) {
        return assignmentsService.getSiteAssignments(siteId)
                .map(AssignmentsResponse::getAssignments);
    }

}
