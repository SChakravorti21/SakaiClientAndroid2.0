package com.example.development.sakaiclient20.repositories;

import android.os.AsyncTask;

import com.example.development.sakaiclient20.models.sakai.assignments.AssignmentsResponse;
import com.example.development.sakaiclient20.networking.services.AssignmentsService;
import com.example.development.sakaiclient20.persistence.access.AssignmentDao;
import com.example.development.sakaiclient20.persistence.composites.CompositeAssignment;
import com.example.development.sakaiclient20.persistence.entities.Assignment;

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

    public Single<List<Assignment>> getAllAssignments(boolean refresh) {
        if(refresh) {
            return assignmentsService
                    .getAllAssignments()
                    .map(this::persistAssignments);
        } else {
            return assignmentDao
                    .getAllAssignments()
                    .firstOrError()
                    .map(this::convertCompositesToDTO);
        }
    }

    public Single<List<Assignment>> getAssignmentsForSite(String siteId, boolean refresh) {
        if(refresh) {
            return assignmentsService
                    .getSiteAssignments(siteId)
                    .map(this::persistAssignments);
        } else {
            return assignmentDao
                    .getAssignmentsForSite(siteId)
                    .firstOrError()
                    .map(this::convertCompositesToDTO);
        }
    }

    private List<Assignment> persistAssignments(AssignmentsResponse response) {
        List<Assignment> assignments = response.getAssignments();
        InsertAssignmentsTask task = new InsertAssignmentsTask(assignmentDao);

        // Using generic varargs can supposedly pollute the heap,
        // so convert to array before passing as task argument
        task.execute(assignments.toArray(new Assignment[assignments.size()]));

        return assignments;
    }

    private List<Assignment> convertCompositesToDTO(List<CompositeAssignment> assignmentEntities) {
        List<Assignment> assignmentDTOs = new ArrayList<>(assignmentEntities.size());

        for(CompositeAssignment composite : assignmentEntities) {
            Assignment assignment = composite.assignment;
            assignment.attachments = composite.attachments;
            assignmentDTOs.add(assignment);
        }

        return assignmentDTOs;
    }

    private static class InsertAssignmentsTask extends AsyncTask<Assignment, Void, Void> {

        private WeakReference<AssignmentDao> assignmentDao;

        private InsertAssignmentsTask(AssignmentDao assignmentDao) {
            this.assignmentDao = new WeakReference<>(assignmentDao);
        }

        @Override
        protected Void doInBackground(Assignment... assignments) {
            if(assignmentDao == null || assignmentDao.get() == null)
                return null;

            assignmentDao.get().insert(assignments);
            return null;
        }
    }

}
