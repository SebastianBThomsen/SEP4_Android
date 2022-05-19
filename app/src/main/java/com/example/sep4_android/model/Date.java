package com.example.sep4_android.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "date_table")
public class Date {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private long date;

    public Date(long date){
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public long getIdk() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdk(long date) {
        this.date = date;
    }
}
