package com.example.development.sakaiclient20.persistence.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Development on 8/5/18.
 */

@Entity(tableName = "grades",
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                                    parentColumns = "siteId",
                                    childColumns = "siteId",
                                    onDelete = ForeignKey.CASCADE,
                                    onUpdate = ForeignKey.CASCADE),
        indices = @Index(value = "courseId"))
public class GradeEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public final int siteId;
    public final String grade;
    public final String itemName;
    public final double points;

    public GradeEntity(final int siteId, String grade, String itemName, double points) {
        this.siteId = siteId;
        this.grade = grade;
        this.itemName = itemName;
        this.points = points;
    }
}
