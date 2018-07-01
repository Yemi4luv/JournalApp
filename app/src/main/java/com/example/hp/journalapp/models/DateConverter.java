package com.example.hp.journalapp.models;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by opeyemi.adeniyi on 6/28/2018.
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
