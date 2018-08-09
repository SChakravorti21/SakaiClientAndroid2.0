package com.example.development.sakaiclient20.repositories;

import com.example.development.sakaiclient20.networking.services.AssignmentsService;
import com.example.development.sakaiclient20.persistence.access.AssignmentDao;
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

    public Single<List<AssignmentEntity>> getAssignmentsForSite(String siteId) {
        return assignmentDao.getAssignmentsForSite(siteId)
                .map(assignmentEntity -> {
                    assignmentDao
                            .getAttachmentsForAssignment(assignmentEntity.getAssignmentId())
                            .forEach(assignmentEntity::addAttachment);
                    return assignmentEntity;
                })
                .toList();
    }

}
