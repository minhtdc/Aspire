package com.example.aspire;

public class Post {
    String userAvatar, userName, userPosition, userTimePost, userTitlePost, userContentPost, userViewPost, userCountCommentPost;

    public Post(String userAvatar, String userName, String userPosition, String userTimePost, String userTitlePost, String userContentPost, String userViewPost, String userCountCommentPost) {
        this.userAvatar = userAvatar;
        this.userName = userName;
        this.userPosition = userPosition;
        this.userTimePost = userTimePost;
        this.userTitlePost = userTitlePost;
        this.userContentPost = userContentPost;
        this.userViewPost = userViewPost;
        this.userCountCommentPost = userCountCommentPost;
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
