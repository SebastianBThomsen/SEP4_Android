package com.example.sep4_android.model.persistence;

import androidx.room.TypeConverter;

import com.example.sep4_android.model.persistence.entities.Measurement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<Measurement> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Measurement>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Measurement> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
