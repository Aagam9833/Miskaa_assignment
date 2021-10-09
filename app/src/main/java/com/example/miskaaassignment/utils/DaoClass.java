package com.example.miskaaassignment.utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.miskaaassignment.model.Crew;

import java.util.List;

@Dao
public interface DaoClass {

    @Insert
    void insertAll(Crew crew);

    @Query("select * from crew")
    List<Crew> getAllData();

    @Query("delete from crew")
    public void deleteAllData();

    @Query("select count(*) from crew")
    public boolean dataExist();

}
