package com.example.aspire.data_models;

import com.example.aspire.R;

public class Post {
    int userAvatar;
    String userName, userPosition, userTimePost, userTitlePost, userContentPost, userViewPost, userCountCommentPost;

    public Post(String userName, String userPosition, String userTimePost, String userTitlePost, String userContentPost, String userViewPost, String userCountCommentPost) {
        this.userAvatar = R.drawable.avt;
        this.userName = userName;
        this.userPosition = userPosition;
        this.userTimePost = userTimePost;
        this.userTitlePost = userTitlePost;
        this.userContentPost = userContentPost;
        this.userViewPost = userViewPost;
        this.userCountCommentPost = userCountCommentPost;
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

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserTimePost() {
        return userTimePost;
    }

    public void setUserTimePost(String userTimePost) {
        this.userTimePost = userTimePost;
    }

    public String getUserTitlePost() {
        return userTitlePost;
    }

    public void setUserTitlePost(String userTitlePost) {
        this.userTitlePost = userTitlePost;
    }

    public String getUserContentPost() {
        return userContentPost;
    }

    public void setUserContentPost(String userContentPost) {
        this.userContentPost = userContentPost;
    }

    public String getUserViewPost() {
        return userViewPost;
    }

    public void setUserViewPost(String userViewPost) {
        this.userViewPost = userViewPost;
    }

    public String getUserCountCommentPost() {
        return userCountCommentPost;
    }

    public void setUserCountCommentPost(String userCountCommentPost) {
        this.userCountCommentPost = userCountCommentPost;
    }
}
