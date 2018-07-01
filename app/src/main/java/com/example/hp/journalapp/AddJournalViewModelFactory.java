package com.example.hp.journalapp;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.hp.journalapp.models.AppDatabase;

/**
 * Created by opeyemi.adeniyi on 6/29/2018.
 */

public class AddJournalViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final int mJournalId;

    public AddJournalViewModelFactory(AppDatabase database, int JournalId) {
        mDb = database;
        mJournalId = JournalId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddJournalViewModel(mDb, mJournalId);
    }
}
