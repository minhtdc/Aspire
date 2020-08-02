package com.example.aspire.data_models;

import android.util.Log;

import com.example.aspire.R;
import com.example.aspire.android_2_func;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Post extends Groups{
    int userAvatar;
    String userName, userPosition, userTimePost, userTitlePost, userContentPost, userViewPost, userCountCommentPost;
    String postID, userID;

    public Post(String userName, String userPosition, String userTimePost, String userTitlePost, String userContentPost, String userViewPost, String userCountCommentPost, String userID) {
        this.userAvatar = R.drawable.avt;
        this.userName = userName;
        this.userPosition = userPosition;
        this.userTimePost = userTimePost;
        this.userTitlePost = userTitlePost;
        this.userContentPost = userContentPost;
        this.userViewPost = userViewPost;
        this.userCountCommentPost = userCountCommentPost;
        this.userID = userID;
    }

    public Post() {

    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public void addPostToDatabase(Post post) throws JSONException {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> postValues = null;

        try {
            postValues = android_2_func.toMap(post.toJSON(post));
        } catch (JSONException e) {
            Log.d("JSON Posts: ", post.toJSON(post).toString());
        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/groups/" + post.getGroupID() + "/posts/" + post.getPostID(), postValues);

        mDatabase.updateChildren(childUpdates);
    }

    public JSONObject toJSON(Post post) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("id", post.getPostID());
        json.put("title", post.getUserTitlePost());
        json.put("content", post.getUserContentPost());
        json.put("userID", post.getUserID());
        return json;
    }
}
