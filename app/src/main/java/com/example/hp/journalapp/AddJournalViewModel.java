package com.example.hp.journalapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.hp.journalapp.models.AppDatabase;
import com.example.hp.journalapp.models.DiaryEntry;

/**
 * Created by opeyemi.adeniyi on 6/29/2018.
 */

public class AddJournalViewModel extends ViewModel {

    private LiveData<DiaryEntry> diaryData;

    public AddJournalViewModel(AppDatabase database, int journalId) {
        diaryData = database.journalDao().loadTaskById(journalId);
    }
    public LiveData<DiaryEntry> getDiaryData() {
        return diaryData;
    }
}
