package com.example.aspire.data_models;

import android.util.Log;

import com.example.aspire.android_2_func;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Groups {
    private String groupID, adminId, memberID, groupName;
    private DatabaseReference mDatabase;

    public Groups() {
    }

    public Groups(String groupID, String adminId, String memberID, String groupName) {
        this.groupID = groupID;
        this.adminId = adminId;
        this.memberID = memberID;
        this.groupName = groupName;
    }

    //get/set
    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    //cac ham


}

