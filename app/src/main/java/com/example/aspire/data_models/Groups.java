package com.example.aspire.data_models;

import android.util.Log;

<<<<<<< HEAD
import com.example.aspire.android_2_func;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
=======
import androidx.annotation.NonNull;

import com.example.aspire.android_2_func;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
>>>>>>> Văn-Bi

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Groups {
    private String groupID, adminId, groupName, groupInfo;
    private DatabaseReference mDatabase;
    private ArrayList<String> listIDMember;

    public Groups() {
        listIDMember = new ArrayList<String>();
    }

    public Groups(String groupID, String adminId, String groupName, String groupInfo, ArrayList<String> listIDMember) {
        this.groupID = groupID;
        this.adminId = adminId;
        this.groupName = groupName;
        this.groupInfo = groupInfo;
        this.listIDMember = new ArrayList<String>();
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public void setmDatabase(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }

    public ArrayList<String> getListIDMember() {
        return listIDMember;
    }

    public String getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(String groupInfo) {
        this.groupInfo = groupInfo;
    }

    public void setListIDMember(ArrayList<String> listIDMember) {
        this.listIDMember = listIDMember;
    }

<<<<<<< HEAD
    //ham ađ group
=======
    //ham add group
>>>>>>> Văn-Bi
    public void addGroupToDatabase(Groups group) throws JSONException {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> postValues = null;

        try {
            postValues = android_2_func.toMap(group.toJSON(group));
        } catch (JSONException e) {
            Log.d("JSON User: ", group.toJSON(group).toString());
        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/groups/" + group.getGroupID(), postValues);

        mDatabase.updateChildren(childUpdates);
    }

<<<<<<< HEAD
=======
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


>>>>>>> Văn-Bi
    //
    public JSONObject toJSON(Groups group) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("group_name", group.getGroupName());
        json.put("group_info", group.getGroupInfo());
        json.put("admin_id", group.getAdminId());
        return json;
<<<<<<< HEAD
    }

=======

    }
>>>>>>> Văn-Bi
}

