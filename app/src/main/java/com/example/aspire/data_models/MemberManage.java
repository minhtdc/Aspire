package com.example.aspire.data_models;

import android.util.Log;

import com.example.aspire.android_2_func;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MemberManage {
    String userAvatar,memberID, adminID, userName,groupID;
    private com.example.aspire.android_2_func android_2_func;
    private DatabaseReference mDatabase;

    public MemberManage(String userAvatar,String adminID, String userName,String groupID,String memberID) {
        this.userAvatar = userAvatar;
        this.userName = userName;
        this.groupID = groupID;
        this.adminID = adminID;
        this.memberID = memberID;

    }
    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public void setmDatabase(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }
    public MemberManage() { android_2_func = new android_2_func();
    }
    public JSONObject toJSON(MemberManage memberManage) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("memberID", memberManage.getMemberID());
        json.put("groupID", memberManage.getGroupID());
        json.put("adminID", memberManage.getAdminID());
        json.put("user_name", memberManage.getUserName());
        json.put("avatar", Character.toString(memberManage.getUserAvatar().toUpperCase().charAt(0)));
        return json;
    }
}
