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
 private String groupID, adminId, memberID, postID, groupName;
 private Users users;
 private Post post;
 private DatabaseReference mDatabase;

    private android_2_func android_2_func;

    public Groups() {
        android_2_func = new android_2_func();
    }

    public Groups(String adminId, String memberID, String groupName) {
        this.adminId = adminId;
        this.memberID = memberID;
        this.groupName = groupName;
        android_2_func = new android_2_func();
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

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    //add group to database
    public void addGroupToDatabase(String groupID, Groups group) throws JSONException {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> postValues = null;
        try {
            postValues = android_2_func.toMap(group.toJSON(group));
        } catch (JSONException e) {
        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/groups/" + groupID, postValues);

        mDatabase.updateChildren(childUpdates);
    }


    //
    public JSONObject toJSON(Groups groups) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("adminID", groups.getAdminId());
        json.put("memberID", groups.getMemberID());
        json.put("groupName", groups.getGroupName());
        return json;
    }

}
