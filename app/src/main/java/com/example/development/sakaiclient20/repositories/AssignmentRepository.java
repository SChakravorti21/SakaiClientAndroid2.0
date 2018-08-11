package com.example.development.sakaiclient20.repositories;

import android.os.AsyncTask;

import com.example.development.sakaiclient20.common.converters.AssignmentConverter;
import com.example.development.sakaiclient20.models.sakai.assignments.Assignment;
import com.example.development.sakaiclient20.models.sakai.assignments.AssignmentsResponse;
import com.example.development.sakaiclient20.networking.services.AssignmentsService;
import com.example.development.sakaiclient20.persistence.access.AssignmentDao;
import com.example.development.sakaiclient20.persistence.composites.AssignmentWithAttachments;
import com.example.development.sakaiclient20.persistence.entities.AssignmentEntity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

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

    public Single<List<Assignment>> getAssignmentsForSite(String siteId, boolean refresh) {
        if(refresh) {
             return getSiteAssignmentsApi(siteId);
        } else {
             return getSiteAssignmentsDb(siteId);
        }
    }

    private Single<List<Assignment>> getSiteAssignmentsDb(String siteId) {
        return assignmentDao
                .getAssignmentsForSite(siteId)
                .firstOrError()
                .map(assignmentEntities -> {
                    List<Assignment> assignmentDTOs = new ArrayList<>(assignmentEntities.size());
                    AssignmentConverter assignmentConverter = new AssignmentConverter();

                    for(AssignmentWithAttachments entity : assignmentEntities) {
                        AssignmentEntity assignmentEntity = entity.assignment;
                        assignmentEntity.attachments = entity.attachments;

                        assignmentDTOs.add(assignmentConverter.fromEntity(assignmentEntity));
                    }

                    return assignmentDTOs;
                });
    }

    private Single<List<Assignment>> getSiteAssignmentsApi(String siteId) {
        return assignmentsService.getSiteAssignments(siteId)
                .map(response -> {
                    // TODO: Where should this be done?
                    List<Assignment> assignments = response.getAssignments();
                    InsertAssignmentsTask task = new InsertAssignmentsTask(assignmentDao);

                    // Using generic varargs can supposedly pollute the heap,
                    // so convert to array before passing as task argument
                    task.execute(assignments.toArray(new Assignment[assignments.size()]));

                    return assignments;
                });
    }


    private static class InsertAssignmentsTask extends AsyncTask<Assignment, Void, Void> {

        private WeakReference<AssignmentDao> assignmentDao;

        private InsertAssignmentsTask(AssignmentDao assignmentDao) {
            this.assignmentDao = new WeakReference<>(assignmentDao);
        }

        @Override
        protected Void doInBackground(Assignment... assignments) {
            AssignmentConverter converter = new AssignmentConverter();
            AssignmentEntity[] assignmentEntities = new AssignmentEntity[assignments.length];
            for(int index = 0; index < assignments.length; index++) {
                assignmentEntities[index] = converter.fromDTO(assignments[index]);
            }

            if(assignmentDao == null || assignmentDao.get() == null)
                return null;

            assignmentDao.get().insert(assignmentEntities);
            return null;
        }
    }

}
