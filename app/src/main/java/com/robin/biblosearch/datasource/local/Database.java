package com.robin.biblosearch.datasource.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.robin.biblosearch.models.Item;
import com.robin.biblosearch.models.VolumeInfo;

@androidx.room.Database(entities = {VolumeInfo.class}, exportSchema = false, version = 1)
@TypeConverters({DateConverter.class, Converters.class})
public abstract class Database extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "bookDatabase";
    private static Database sInstance;

    public static Database getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class, Database.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }

    public abstract BookDao bookDao();
}
