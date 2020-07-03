package com.example.tiktokdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Video.class}, version = 1, exportSchema = false)
public abstract class TikTokDatabase extends RoomDatabase {

    public abstract TikTokDAO getTikTokDAO();
}

