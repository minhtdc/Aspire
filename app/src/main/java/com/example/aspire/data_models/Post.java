package com.example.aspire.data_models;

import com.example.aspire.R;

public class Post {
    String commentID, contentPost, titlePost, userID, postID, countCommentPost;

    public Post() {
        this.commentID = "";
        this.contentPost = "";
        this.titlePost = "";
        this.userID = "";
        this.postID = "";
        this.countCommentPost = "";
    }

    public Post(String commentID, String contentPost, String titlePost, String userID, String postID, String countCommentPost) {
        this.commentID = commentID;
        this.contentPost = contentPost;
        this.titlePost = titlePost;
        this.userID = userID;
        this.postID = postID;
        this.countCommentPost = countCommentPost;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getContentPost() {
        return contentPost;
    }

    public void setContentPost(String contentPost) {
        this.contentPost = contentPost;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getCountCommentPost() {
        return countCommentPost;
    }

    public void setCountCommentPost(String countCommentPost) {
        this.countCommentPost = countCommentPost;
    }
}
