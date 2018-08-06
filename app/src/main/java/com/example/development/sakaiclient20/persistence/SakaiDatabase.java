package com.example.development.sakaiclient20.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.development.sakaiclient20.persistence.converters.DateConverter;
import com.example.development.sakaiclient20.persistence.entities.AssignmentEntity;
import com.example.development.sakaiclient20.persistence.entities.AttachmentEntity;
import com.example.development.sakaiclient20.persistence.entities.CourseEntity;
import com.example.development.sakaiclient20.persistence.entities.GradeEntity;

/**
 * Created by Development on 8/5/18.
 */

@Database(entities = {
            CourseEntity.class,
            GradeEntity.class,
            AssignmentEntity.class,
            AttachmentEntity.class
        }, version = 1)
@TypeConverters({DateConverter.class})
public abstract class SakaiDatabase extends RoomDatabase {

    private static volatile SakaiDatabase mInstance;

    public static SakaiDatabase getInstance(Context context) {
        if(mInstance == null) {
            synchronized (mInstance) {
                if(mInstance == null) {
                    mInstance = Room
                            .databaseBuilder(context, SakaiDatabase.class, "Sakai.db")
                            .build();
                }
            }
        }

        return mInstance;
    }
}
