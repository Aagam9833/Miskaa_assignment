package com.example.miskaaassignment.model;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.miskaaassignment.utils.DaoClass;

@androidx.room.Database(entities = {Crew.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract DaoClass getDao();

    private static Database instance;

    public static Database getDatabase(final Context context) {
        if (instance == null){
            synchronized (Database.class){
                instance = Room.databaseBuilder(context,Database.class,"Database").allowMainThreadQueries().build();
            }
        }
        return instance;
    }

}
