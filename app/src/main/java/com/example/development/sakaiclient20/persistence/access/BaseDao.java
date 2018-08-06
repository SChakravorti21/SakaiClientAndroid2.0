package com.example.development.sakaiclient20.persistence.access;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

/**
 * Created by Development on 8/5/18.
 */

public interface BaseDao<TEntity> {

    @Insert
    void insert(TEntity... entities);

    @Update
    void update(TEntity... entities);

    @Delete
    void delete(TEntity... entities);

}
