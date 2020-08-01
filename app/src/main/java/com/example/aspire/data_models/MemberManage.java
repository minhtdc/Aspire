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
    String userAvatar, userName;
    private com.example.aspire.android_2_func android_2_func;
    private DatabaseReference mDatabase;

    public MemberManage(String userAvatar, String userName) {
        this.userAvatar = userAvatar;
        this.userName = userName;
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

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public void setmDatabase(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }
    public MemberManage() { android_2_func = new android_2_func();
    }
    private void delMemberManageInformation(String NameID, MemberManage memberManage) throws JSONException {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> postValues = null;
        Log.d("JSON Manage: ", memberManage.toJSON(memberManage).toString());

        try {
            postValues = android_2_func.toMap(memberManage.toJSON(memberManage));
        } catch (JSONException e) {
        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/manages/" + NameID, postValues);

        mDatabase.updateChildren(childUpdates);

    }
    public JSONObject toJSON(MemberManage memberManage) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("user_name", memberManage.getUserName());
        json.put("avatar", Character.toString(memberManage.getUserAvatar().toUpperCase().charAt(0)));
        return json;
    }
}
