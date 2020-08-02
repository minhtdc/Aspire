package com.example.aspire.data_models;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aspire.LoginActivity;
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

public class Users {
    private String userID;
    private String userName;
    private String fullName;
    private String userPass;
    private String userAvatar;
    private String email;
    private FirebaseAuth fAuth;

    private android_2_func android_2_func;

    public Users() {
        android_2_func = new android_2_func();
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            this.userID = fAuth.getCurrentUser().getUid();
            // Get a reference to our posts
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
        }
    }

    public Users(String userName, String userPass, String email, String fullName) {
        this.userName = userName;
        this.userPass = userPass;
        this.userAvatar = userAvatar;
        this.email = email;
        this.fullName = fullName;
        android_2_func = new android_2_func();

        if (fAuth.getCurrentUser() != null) {
            this.userID = fAuth.getCurrentUser().getUid();
            // Get a reference to our posts
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
        }
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void createUserWithEmailAndPassword(final Dialog epicDialog, final Context context, final Users user) throws InterruptedException {
        fAuth = FirebaseAuth.getInstance();
        android_2_func.showLoading(context);

        fAuth.createUserWithEmailAndPassword(user.getEmail(), user.getUserPass()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {
                //Starting a new Intent
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (task.isSuccessful()) {
                            try {
                                addUserInformation(fAuth.getUid(), user);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Notification.signUp(epicDialog, context, "Đăng ký thành công", String.format("Bạn đã đăng ký thành công với email là %s, hãy thử đăng nhập ngay nào!", email), true);
                        } else {
                            Notification.signUp(epicDialog, context, "Đăng ký không thành công", "Có lẻ tài khoản của bạn đã trùng với ai đó hoặc có vấn đề về máy chủ", false);
                        }
                        android_2_func.closeLoading();
                    }
                }, 1000);

            }
        });
    }

    private void addUserInformation(String UserID, Users user) throws JSONException {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> postValues = null;
        Log.d("JSON User: ", user.toJSON(user).toString());

        try {
            postValues = android_2_func.toMap(user.toJSON(user));
        } catch (JSONException e) {
        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + UserID, postValues);

        mDatabase.updateChildren(childUpdates);
    }

    public void loginUserWithEmailAndPassword(final String email, String password, final Dialog epicDialog, final Context context) throws InterruptedException {
        android_2_func.showLoading(context);
        final FirebaseAuth fAuth = FirebaseAuth.getInstance();

        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {
                //Starting a new Intent
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (task.isSuccessful()) {
                            SwitchActivity.goToNewFeed(context);
                            Activity activity = (Activity) context;
                            activity.finish();
                        } else {
                            Notification.error(epicDialog, context, "Đăng nhập không thành công", "Tài khoản này không được xác định từ máy chủ của chúng tôi");
                        }
                        android_2_func.closeLoading();
                    }
                }, 1000);
            }
        });
    }

    public boolean isLogged() {
        fAuth = FirebaseAuth.getInstance();
        return fAuth.getCurrentUser() != null;
    }

    public JSONObject toJSON(Users user) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("userName", user.getUserName());
        json.put("userID", user.getUserID());
        json.put("fullName", user.getFullName());
        json.put("userAvatar", Character.toString(user.getFullName().toUpperCase().charAt(0)));
        return json;
    }
}
