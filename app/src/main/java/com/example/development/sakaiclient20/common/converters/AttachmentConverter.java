package com.example.development.sakaiclient20.common.converters;

import com.example.development.sakaiclient20.models.sakai.assignments.Attachment;
import com.example.development.sakaiclient20.persistence.entities.AttachmentEntity;

import org.apache.commons.lang3.NotImplementedException;

/**
 * Created by Development on 8/11/18.
 */

public class AttachmentConverter extends AbstractConverter<AttachmentEntity, Attachment> {
    @Override
    public AttachmentEntity fromDTO(Attachment DTO) {
        AttachmentEntity entity = new AttachmentEntity();

        entity.name = DTO.getName();
        entity.url = DTO.getUrl();

        return entity;
    }

    @Override
    public Attachment fromEntity(AttachmentEntity attachmentEntity) {
        Attachment attachment = new Attachment();

        attachment.setName(attachmentEntity.name);
        attachment.setUrl(attachmentEntity.url);

        return attachment;
    }
}
