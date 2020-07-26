package com.example.aspire.data_models;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.aspire.Notification;
import com.example.aspire.SwitchActivity;
import com.example.aspire.android_2_func;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Newfeed {
    private DatabaseReference mDatabase;
    private String userAvatar;
    private String userName;
    private String userPeople;
    private android_2_func android_2_func;

    public Newfeed(DatabaseReference mDatabase, String userAvatar, String userName, String userPeople) {
        this.mDatabase = mDatabase;
        this.userAvatar = userAvatar;
        this.userName = userName;
        this.userPeople = userPeople;
    }

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public void setmDatabase(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
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
    public Newfeed() {
        android_2_func = new android_2_func();
    }
    private void delGroupInformation(String GroupID, Newfeed newfeed) throws JSONException {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> postValues = null;
        Log.d("JSON Group: ", newfeed.toJSON(newfeed).toString());

        try {
            postValues = android_2_func.toMap(newfeed.toJSON(newfeed));
        } catch (JSONException e) {
        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/groups/" + GroupID, postValues);

        mDatabase.updateChildren(childUpdates);

    }
    private void SeachGroupInformation(String GroupID,String userNameID, Newfeed newfeed) throws JSONException {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> postValues = null;
        Log.d("JSON Group: ", newfeed.toJSON(newfeed).toString());

        try {
            postValues = android_2_func.toMap(newfeed.toJSON(newfeed));
        } catch (JSONException e) {
        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/groups/" + GroupID + userNameID, postValues);

        mDatabase.updateChildren(childUpdates);
    }
    public JSONObject toJSON(Newfeed newfeed) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("user_name", newfeed.getUserName());
        json.put("user_people", newfeed.getUserPeople());
        json.put("avatar", Character.toString(newfeed.getUserAvatar().toUpperCase().charAt(0)));
        return json;
    }
}
