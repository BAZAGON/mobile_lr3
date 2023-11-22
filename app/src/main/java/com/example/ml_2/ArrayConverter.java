package com.example.ml_2;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class ArrayConverter {
    public static Gson gson = new Gson();

    @TypeConverter
    public static String[] fromString(String value) {
        return gson.fromJson(value, String[].class);
    }

    @TypeConverter
    public static String toString(String[] array) {
        return gson.toJson(array);
    }
}
