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
    String userID, postID, postContent, postTitle;

    public Post() {
        this.userID = "";
        this.postID = "";
        this.postContent = "";
        this.postTitle = "";
    }

    public Post(String userID, String postID, String postContent, String postTitle) {
        this.userID = userID;
        this.postID = postID;
        this.postContent = postContent;
        this.postTitle = postTitle;
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

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
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
        json.put("postID", post.getPostID());
        json.put("postTitle", post.getPostTitle());
        json.put("postContent", post.getPostContent());
        json.put("userID", post.getUserID());
        return json;
    }
}
