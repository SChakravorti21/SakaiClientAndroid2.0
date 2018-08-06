package com.example.development.sakaiclient20.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Development on 8/5/18.
 */

@Entity(tableName = "attachments",
        foreignKeys = @ForeignKey(entity = AssignmentEntity.class,
                                    parentColumns = "id",
                                    childColumns = "assignmentId",
                                    onDelete = ForeignKey.CASCADE,
                                    onUpdate = ForeignKey.CASCADE))
public class AttachmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public final String name;
    public final String url;
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
}
