package com.example.aspire.data_models;

public class Newfeed {
    String userAvatar, userName, userPeople;

    public Newfeed() {
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

    public String getUserPeople() {
        return userPeople;
    }

    public void setUserPeople(String userPeople) {
        this.userPeople = userPeople;
    }

    public Newfeed(String userAvatar, String userName, String userPeople) {
        this.userAvatar = userAvatar;
        this.userName = userName;
        this.userPeople = userPeople;
    }
}
