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
    private int userAvatar;
    private String userName;
    private String userComment;
    private ArrayList<Comments> listComments;
    FirebaseDatabase database;
    DatabaseReference mDatabase;

    public Comments(String userName, String userComment) {
        this.userAvatar = R.drawable.anhdaidien;
        this.userName = userName;
        this.userComment = userComment;
    }

    public int getUserAvatar() {
        return userAvatar;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
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

    public JSONObject toJSON(Comments comment) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("userName", comment.getUserName());
        json.put("userComment", comment.getUserComment());
        json.put("userAvatar", Character.toString(comment.getUserName().toUpperCase().charAt(0)));
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