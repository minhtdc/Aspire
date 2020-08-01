package com.example.aspire.data_models;

import android.util.Log;

import com.example.aspire.android_2_func;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Requests {
    private String memberID, adminID, groupID, content;

    public Requests() {
    }

    public Requests(String memberID, String adminID, String groupID, String content) {
        this.memberID = memberID;
        this.adminID = adminID;
        this.groupID = groupID;
        this.content = content;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addRequestsToDatabase(Requests request) throws JSONException {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> postValues = null;

        try {
            postValues = android_2_func.toMap(request.toJSON(request));
        } catch (JSONException e) {
        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/requests/" + request.getGroupID(), postValues);

        mDatabase.updateChildren(childUpdates);
    }

    public JSONObject toJSON(Requests request) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("adminID", request.getAdminID());
        json.put("memberID", request.getMemberID());
        json.put("content", request.getContent());
        return json;
    }
}
