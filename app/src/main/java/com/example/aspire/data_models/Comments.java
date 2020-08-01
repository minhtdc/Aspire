package com.example.aspire.data_models;

import com.example.aspire.R;

public class Comments {
    private int userAvatar;
    private String userName;
    private String userComment;

    public Comments(String userName, String userComment) {
        this.userAvatar = R.drawable.anhdaidien;
        this.userName = userName;
        this.userComment = userComment;
    }

    public int getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(int userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

}