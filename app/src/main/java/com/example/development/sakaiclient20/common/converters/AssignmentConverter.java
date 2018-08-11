package com.example.development.sakaiclient20.common.converters;

import com.example.development.sakaiclient20.models.sakai.assignments.Assignment;
import com.example.development.sakaiclient20.models.sakai.assignments.Attachment;
import com.example.development.sakaiclient20.persistence.entities.AssignmentEntity;
import com.example.development.sakaiclient20.persistence.entities.AttachmentEntity;

/**
 * Created by Development on 8/11/18.
 */

public class AssignmentConverter extends AbstractConverter<AssignmentEntity, Assignment> {

    @Override
    public AssignmentEntity fromDTO(Assignment DTO) {

        AssignmentEntity entity = new AssignmentEntity();

        // Key assignment details
        entity.assignmentId = DTO.assignmentId;
        entity.term = DTO.term;
        entity.title = DTO.title;
        entity.context = DTO.context;
        entity.instructions = DTO.instructions;

        // Information that allows Sakai to keep track of the assignment
        entity.entityURL = DTO.entityURL;
        entity.entityId = DTO.entityId;
        entity.entityTitle = DTO.entityTitle;
        entity.entityReference = DTO.entityReference;

        // Information regarding the submission of assignment
        entity.status = DTO.status;
        entity.dueTime = DTO.getDueTime();
        entity.allowResubmission = DTO.allowResubmission;

        // Information about who created the assignment
        entity.creator = DTO.creator;
        entity.authorLastModified = DTO.authorLastModified;

        // Information regarding the grading scale
        entity.gradeScale = DTO.gradeScale;
        entity.gradeScaleMaxPoints = DTO.gradeScaleMaxPoints;

        AttachmentConverter attachmentConverter = new AttachmentConverter();
        entity.attachments = attachmentConverter.fromDTOs(DTO.attachments);

        for(AttachmentEntity attachmentEntity : entity.attachments) {
            attachmentEntity.assignmentId = entity.assignmentId;
        }

        return entity;
    }

    @Override
    public Assignment fromEntity(AssignmentEntity entity) {

        Assignment DTO = new Assignment();

        // Key assignment details
        DTO.assignmentId = entity.assignmentId;
        DTO.term = entity.term;
        DTO.title = entity.title;
        DTO.context = entity.context;
        DTO.instructions = entity.instructions;

        // Information that allows Sakai to keep track of the assignment
        DTO.entityURL = entity.entityURL;
        DTO.entityId = entity.entityId;
        DTO.entityTitle = entity.entityTitle;
        DTO.entityReference = entity.entityReference;

        // Information regarding the submission of assignment
        DTO.status = entity.status;
        DTO.setDueTime(entity.dueTime);
        DTO.allowResubmission = entity.allowResubmission;

        // Information about who created the assignment
        DTO.creator = entity.creator;
        DTO.authorLastModified = entity.authorLastModified;

        // Information regarding the grading scale
        DTO.gradeScale = entity.gradeScale;
        DTO.gradeScaleMaxPoints = entity.gradeScaleMaxPoints;

        AttachmentConverter attachmentConverter = new AttachmentConverter();
        DTO.attachments = attachmentConverter.fromEntities(entity.attachments);

        return DTO;
    }
}
