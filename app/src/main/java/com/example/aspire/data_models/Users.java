package com.example.aspire.data_models;

import android.media.Image;

public class Users {
    private String userID, userName, userPass, userAvata;

    public Users() {
    }

    public Users(String userID, String userName, String userPass, String userAvata) {
        this.userID = userID;
        this.userName = userName;
        this.userPass = userPass;
        this.userAvata = userAvata;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserAvata() {
        return userAvata;
    }

    public void setUserAvata(String userAvata) {
        this.userAvata = userAvata;
    }
}
