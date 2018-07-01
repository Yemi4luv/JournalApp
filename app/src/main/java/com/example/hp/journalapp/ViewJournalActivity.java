package com.example.hp.journalapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.journalapp.models.AppDatabase;
import com.example.hp.journalapp.models.DiaryEntry;

import java.util.Date;

public class ViewJournalActivity extends AppCompatActivity {
    // Extra for the task ID to be received in the intent
    public static final String EXTRA_TASK_ID = "extraTaskId";
    // Extra for the task ID to be received after rotation
    public static final String INSTANCE_TASK_ID = "instanceTaskId";
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;
    // Constant for logging
    private static final String TAG = AddJournalItemActivity.class.getSimpleName();
    // Fields for views
    EditText mTextView, mTextView2;
    //RadioGroup mRadioGroup;
    Button mButton;

    private int mDiaryId = DEFAULT_TASK_ID;

    // Member variable for the Database
    private AppDatabase mDb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_journal);

        initViews();

        mDb = AppDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mDiaryId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            mButton.setText(R.string.update_button);
            if (mDiaryId == DEFAULT_TASK_ID) {
                // populate the UI
                mDiaryId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);

                AddJournalViewModelFactory factory = new AddJournalViewModelFactory(mDb, mDiaryId);

                final AddJournalViewModel viewModel
                        = ViewModelProviders.of(this, factory).get(AddJournalViewModel.class);


                viewModel.getDiaryData().observe(this, new Observer<DiaryEntry>() {
                    @Override
                    public void onChanged(@Nullable DiaryEntry diaryEntry) {
                        viewModel.getDiaryData().removeObserver(this);
                        populateUI(diaryEntry);
                    }
                });

            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_TASK_ID, mDiaryId);
        super.onSaveInstanceState(outState);
    }

    /**
     * initViews is called from onCreate to init the member variable views
     */
    private void initViews() {
        mTextView = findViewById(R.id.editTextKeyword);
        mTextView2 = findViewById(R.id.textArea_information);

        mTextView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });

        mButton = findViewById(R.id.saveButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewJournalActivity.this, AddJournalItemActivity.class);
                intent.putExtra(AddJournalItemActivity.EXTRA_TASK_ID, mDiaryId);
                startActivity(intent);
            }
        });
    }

    /**
     * populateUI would be called to populate the UI when in update mode
     *
     * @param task the diaryEntry to populate the UI
     */
    private void populateUI(DiaryEntry task) {
        if (task == null) {
            return;
        }

        mTextView.setText(task.getKeyword());
        mTextView2.setText(task.getEntries());
    }

    /**
     * onSaveButtonClicked is called when the "save" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onSaveButtonClicked() {
        String keyword = mTextView.getText().toString();
        String entryStatements = mTextView2.getText().toString();
        Date date = new Date();

        final DiaryEntry task = new DiaryEntry(keyword, entryStatements, date, date);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mDiaryId == DEFAULT_TASK_ID) {
                    // insert new task
                    mDb.journalDao().insertTask(task);
                } else {
                    //update task
                    task.setId(mDiaryId);
                    mDb.journalDao().updateTask(task);
                }
                finish();
            }
        });
    }

}
