package com.example.development.sakaiclient20.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Development on 8/5/18.
 */

@Entity(tableName = "grades",
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                                    parentColumns = "courseId",
                                    childColumns = "gradeId",
                                    onDelete = ForeignKey.CASCADE,
                                    onUpdate = ForeignKey.CASCADE))
public class GradeEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "gradeId")
    private int id;

    public final String grade;
    public final String itemName;
    public final double points;

    public GradeEntity(String grade, String itemName, double points) {
        this.grade = grade;
        this.itemName = itemName;
        this.points = points;
    }
}
