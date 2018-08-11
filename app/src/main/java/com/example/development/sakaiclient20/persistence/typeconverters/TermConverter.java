package com.example.development.sakaiclient20.persistence.typeconverters;

import android.arch.persistence.room.TypeConverter;

import com.example.development.sakaiclient20.models.custom.Term;

/**
 * Created by Development on 8/5/18.
 */

public class TermConverter {

    @TypeConverter
    public static Term fromTermEid(String termEid) {
        return new Term(termEid);
    }

    @TypeConverter
    public static String toTermEid(Term term) {
        return term.getTermEid();
    }

}
