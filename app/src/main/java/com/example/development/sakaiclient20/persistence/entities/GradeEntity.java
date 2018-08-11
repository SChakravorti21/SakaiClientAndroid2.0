package com.example.development.sakaiclient20.persistence.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Development on 8/5/18.
 */

@Entity(tableName = "grades",
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                                    parentColumns = "id",
                                    childColumns = "courseId",
                                    onDelete = ForeignKey.CASCADE,
                                    onUpdate = ForeignKey.CASCADE))
public class GradeEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public final int courseId;
    public final String grade;
    public final String itemName;
    public final double points;

    public GradeEntity(final int courseId, String grade, String itemName, double points) {
        this.courseId = courseId;
        this.grade = grade;
        this.itemName = itemName;
        this.points = points;
    }
}
