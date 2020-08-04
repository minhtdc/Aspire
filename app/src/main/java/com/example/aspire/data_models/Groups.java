package com.example.aspire.data_models;

import android.util.Log;

import com.example.aspire.android_2_func;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Groups {
    private String groupID, adminID, groupName, groupInfo;
    private DatabaseReference mDatabase;
    private ArrayList<String> listIDMember;

    public Groups() {
        this.groupID = "";
        this.adminID = "";
        this.groupName = "";
        this.groupInfo = "";
    }

    public Groups(String groupID, String adminID, String groupName, String groupInfo, ArrayList<String> listIDMember) {
        this.groupID = groupID;
        this.adminID = adminID;
        this.groupName = groupName;
        this.groupInfo = groupInfo;
        this.listIDMember = listIDMember;
    }


    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(String groupInfo) {
        this.groupInfo = groupInfo;
    }

    public ArrayList<String> getListIDMember() {
        return listIDMember;
    }

    public void setListIDMember(ArrayList<String> listIDMember) {
        this.listIDMember = listIDMember;
    }

    // thêm group vào database
    public void addGroupToDatabase(Groups group) throws JSONException {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> postValues = null;

        try {
            postValues = android_2_func.toMap(group.toJSON(group));
        } catch (JSONException e) {
            Log.d("JSON Groups: ", group.toJSON(group).toString());
        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/groups/" + group.getGroupID(), postValues);

        mDatabase.updateChildren(childUpdates);
    }

//    private void SeachGroupInformation(String GroupID, String userNameID, Newfeed newfeed) throws JSONException {
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        Map<String, Object> postValues = null;
//        Log.d("JSON Group: ", newfeed.toJSON(newfeed).toString());
//
//        try {
//            postValues = android_2_func.toMap(newfeed.toJSON(newfeed));
//        } catch (JSONException e) {
//        }
//
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/groups/" + GroupID + userNameID, postValues);
//
//        mDatabase.updateChildren(childUpdates);
//    }

    //
    public JSONObject toJSON(Groups group) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("groupName", group.getGroupName());
        json.put("groupInfo", group.getGroupInfo());
        json.put("adminID", group.getAdminID());
        json.put("groupID", group.getGroupID());
        return json;
    }
}



