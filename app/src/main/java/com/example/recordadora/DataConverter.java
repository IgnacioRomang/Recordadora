package com.example.recordadora;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DataConverter {
    @TypeConverter
    public static Date toDate(Long fecha){
        return fecha == null ? null: new Date(fecha);
    }

    @TypeConverter
    public static Long fromDate(Date fecha){
        return fecha == null ? null :fecha.getTime();
    }
}
