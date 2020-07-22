package com.example.aspire.data_models;

public class MemberManage {
    String userAvatar, userName;

    public MemberManage() {
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MemberManage(String userAvatar, String userName) {
        this.userAvatar = userAvatar;
        this.userName = userName;

    }
}
