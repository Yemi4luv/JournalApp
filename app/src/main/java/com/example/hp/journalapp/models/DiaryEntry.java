package com.example.hp.journalapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by opeyemi.adeniyi on 6/28/2018.
 */
@Entity(tableName = "journal")
public class DiaryEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String keyword;
    private String entries;
    private int action;
    @ColumnInfo(name = "date_added")
    private Date dateAdded;
    @ColumnInfo(name = "updated_date")
    private Date updatedDate;

    @Ignore
    public DiaryEntry(String keyword, String entries, Date dateAdded, Date updatedDate){
        this.keyword = keyword;
        this.entries = entries;
        //this.action = action;
        this.dateAdded = dateAdded;
        this.updatedDate = updatedDate;
    }


    public DiaryEntry(int id, String keyword, String entries, Date dateAdded, Date updatedDate){
        this.keyword = keyword;
        this.entries = entries;
        //this.action = action;
        this.dateAdded = dateAdded;
        this.updatedDate = updatedDate;
    }

    public int getId() {return  id;}

    public void setId(int id) {this.id = id; }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getEntries() {return entries; }

    public void setEntries(String entries) {this.entries = entries; }

    public int getAction() {return  action; }

    public void setAction(int action) {this.action = action; }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}