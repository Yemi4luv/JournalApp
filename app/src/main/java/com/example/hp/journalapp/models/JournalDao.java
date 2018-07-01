package com.example.hp.journalapp.models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by opeyemi.adeniyi on 6/28/2018.
 */
@Dao
public interface JournalDao {

    @Query("SELECT * FROM journal ORDER BY date_added")
    LiveData<List<DiaryEntry>> loadAllTasks();

    @Insert
    void insertTask(DiaryEntry diaryEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(DiaryEntry diaryEntry);

    @Delete
    void deleteTask(DiaryEntry diarykEntry);

    @Query("SELECT * FROM journal WHERE id = :id")
    LiveData<DiaryEntry> loadTaskById(int id);
}
