package com.example.development.sakaiclient20.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.development.sakaiclient20.persistence.entities.Attachment;
import com.example.development.sakaiclient20.persistence.typeconverters.DateConverter;
import com.example.development.sakaiclient20.persistence.entities.Assignment;
import com.example.development.sakaiclient20.persistence.entities.CourseEntity;
import com.example.development.sakaiclient20.persistence.entities.Grade;
import com.example.development.sakaiclient20.persistence.typeconverters.TermConverter;

/**
 * Created by Development on 8/5/18.
 */

@Database(entities = {
            CourseEntity.class,
            Grade.class,
            Assignment.class,
            Attachment.class
        }, version = 1)
@TypeConverters({DateConverter.class, TermConverter.class})
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
