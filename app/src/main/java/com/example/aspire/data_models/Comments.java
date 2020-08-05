package com.example.aspire.data_models;

import android.util.Log;

import com.example.aspire.R;
import com.example.aspire.android_2_func;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Comments {
    private String idComment;
    private String userID;
    private String content;
    private ArrayList<Comments> listComments;
    DatabaseReference mDatabase;

    public Comments(String userID, String content) {
        this.userID = userID;
        this.content = content;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public JSONObject toJSON(Comments comment) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("userID", Users.ID_USER_LOGGED_IN);
        json.put("content", comment.getContent());
        return json;
    }

    public void addComment(String idComment, String idGroup, String idPost, Comments comment) throws JSONException {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> postValues = null;
        Log.d("JSON User: ", comment.toJSON(comment).toString());

        try {
            postValues = android_2_func.toMap(comment.toJSON(comment));
        } catch (JSONException e) {
        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/groups/" + idGroup + "/posts/" + idPost + "/comments/" + idComment + "/", postValues);

        mDatabase.updateChildren(childUpdates);
    }
}