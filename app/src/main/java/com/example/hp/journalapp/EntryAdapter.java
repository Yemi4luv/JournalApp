/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.hp.journalapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.journalapp.models.DiaryEntry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by opeyemi.adeniyi on 6/28/2018.
 */

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.DiaryViewHolder>{

    private static final String DATE_FORMAT = "dd/MM/yyy";

    // Member variable to handle item clicks
    final private ItemClickListener mItemClickListener;
    // Class variables for the List that holds diary data and the Context
    private List<DiaryEntry> mDiaryEntries;
    private Context mContext;
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public EntryAdapter(Context context, ItemClickListener listener) {
        mItemClickListener = listener;
        mContext = context;
    }


    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.diary_layout, parent, false);

        return new DiaryViewHolder(view);
    }


    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(EntryAdapter.DiaryViewHolder holder, int position) {
        DiaryEntry diaryEntry = mDiaryEntries.get(position);
        String keyword = diaryEntry.getKeyword();
        String entries = diaryEntry.getEntries();
        int action = diaryEntry.getAction();
        String dateAdded = dateFormat.format(diaryEntry.getDateAdded());
        String updateddate = dateFormat.format(diaryEntry.getUpdatedDate());

        holder.keywordEntryView.setText(keyword);
        holder.dateAddedView.setText(dateAdded);

    }


    @Override
    public int getItemCount() {
        if (mDiaryEntries == null) {
            return 0;
        }
        return mDiaryEntries.size();
    }

    public List<DiaryEntry> getmDiaryEntries() {
        return mDiaryEntries;
    }

    public void setmDiaryEntries(List<DiaryEntry> mDiaryEntries) {
        this.mDiaryEntries = mDiaryEntries;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    class DiaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView keywordEntryView;
        TextView diaryEntryView;
        TextView dateAddedView;
        TextView updatedDateView;

        public DiaryViewHolder(View itemView) {
            super(itemView);

            keywordEntryView = itemView.findViewById(R.id.keyword);
            dateAddedView = itemView.findViewById(R.id.date_created);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = mDiaryEntries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);

        }
    }
}
