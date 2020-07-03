package com.example.tiktokdatabase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.ByteArrayOutputStream;


@Entity(tableName = "tik")
public class Video {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tik_id")
    private long id;
    @ColumnInfo(name = "tik_description")
    private String description;
    @ColumnInfo(name = "tik_url")
    private String URL;



    @Ignore
    public Video() {
    }

    public Video(long id, String description, String URL) {
        this.id = id;
        this.description = description;
        this.URL = URL;

    }

    @Ignore
    public Video(String description, String URL) {
        this.description = description;
        this.URL = URL;
    }

    public long getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }



    public String getURL() {
        return URL;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setURL(String URL) {
        this.URL = URL;
    }


}
