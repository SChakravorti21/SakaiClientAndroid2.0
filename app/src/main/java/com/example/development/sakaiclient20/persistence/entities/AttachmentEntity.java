package com.example.development.sakaiclient20.persistence.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.development.sakaiclient20.models.interfaces.IAttachment;

/**
 * Created by Development on 8/5/18.
 */

@Entity(tableName = "attachments",
        foreignKeys = @ForeignKey(entity = AssignmentEntity.class,
                                    parentColumns = "id",
                                    childColumns = "assignmentId",
                                    onDelete = ForeignKey.CASCADE,
                                    onUpdate = ForeignKey.CASCADE))
public class AttachmentEntity implements IAttachment {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String url;
    public final int assignmentId;

    public AttachmentEntity(
            String name,
            String url,
            int assignmentId
    ) {
        this.name = name;
        this.url = url;
        this.assignmentId = assignmentId;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
