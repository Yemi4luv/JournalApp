package com.example.hp.journalapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.hp.journalapp.models.AppDatabase;
import com.example.hp.journalapp.models.DiaryEntry;

import java.util.List;

/**
 * Created by opeyemi.adeniyi on 6/29/2018.
 */
 public class MainViewModel extends AndroidViewModel {

        // Constant for logging
        private static final String TAG = MainViewModel.class.getSimpleName();

        private LiveData<List<DiaryEntry>> entries;

        public MainViewModel(Application application) {
            super(application);
            AppDatabase database = AppDatabase.getInstance(this.getApplication());
            Log.d(TAG, "Actively retrieving the tasks from the DataBase");
            entries = database.journalDao().loadAllTasks();
        }

        public LiveData<List<DiaryEntry>> getEntries() {
            return entries;
        }
}
