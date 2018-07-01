package com.example.hp.journalapp.models;

import java.util.HashMap;

/**
 * Created by opeyemi.adeniyi on 6/28/2018.
 */

public class User {

    private String fullName;
    //private String photo;
    private String email;
    private HashMap<String,Object> timestampJoined;

    public  User() {

    }

    /**
     *
     * @param mFullName
     * @param mEmail
     * @param timestampJoined
     */
    //Creating new user
    public User(String mFullName, String mEmail, HashMap<String, Object> timestampJoined) {
        this.fullName = mFullName;
        this.email = mEmail;
        this.timestampJoined = timestampJoined;


    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }

    public HashMap<String, Object> getTimesstampJoined(){
        return  timestampJoined;
    }
}



