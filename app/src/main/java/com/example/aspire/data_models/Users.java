package com.example.aspire.data_models;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.aspire.Notification;
import com.example.aspire.SwitchActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Users {
    private String userID, userName, userPass, userAvata;
    public static boolean result;
// ...
    public Users() {

    }

    public Users(String userID, String userName, String userPass, String userAvata) {
        this.userID = userID;
        this.userName = userName;
        this.userPass = userPass;
        this.userAvata = userAvata;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserAvata() {
        return userAvata;
    }

    public void setUserAvata(String userAvata) {
        this.userAvata = userAvata;
    }

    public void createUserWithEmailAndPassword(final String email, String password, final Dialog epicDialog, final Context context) throws InterruptedException {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Notification.signUp(epicDialog, context, "Đăng ký thành công", String.format("Bạn đã đăng ký thành công với email là %s, hãy thử đăng nhập ngay nào!", email), true);
                } else {
                    Notification.signUp(epicDialog, context, "Đăng ký không thành công", "Có lẻ tài khoản của bạn đã trùng với ai đó hoặc có vấn đề về máy chủ", false);
                }
            }
        });
    }

    public void loginUserWithEmailAndPassword(final String email, String password, final Dialog epicDialog, final Context context) throws InterruptedException {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //Starting a new Intent
                    SwitchActivity.goToNewFeed(context);
                } else {
                    Notification.error(epicDialog, context, "Đăng nhập không thành công", "Tài khoản này không được xác định từ máy chủ của chúng tôi");
                }
            }
        });
    }

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("user_id", this.userID);
        json.put("password", this.userPass);
        json.put("user_name", this.userID);
        json.put("avatar", this.userAvata);

        return json;
    }
}
