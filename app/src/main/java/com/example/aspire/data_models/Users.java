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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private String task;
    private String colorFavorite;
    private FirebaseAuth fAuth;
    public static String ID_USER_LOGGED_IN;

    private android_2_func android_2_func;

    public Users() {
        this.colorFavorite = "";
        this.fullName = "";
        android_2_func = new android_2_func();
        fAuth = FirebaseAuth.getInstance();
        this.userID = "";

        if (fAuth.getCurrentUser() != null) {
            this.userID = fAuth.getCurrentUser().getUid();
        }
    }

    public Users(String userName, String userPass, String email, String fullName) {
        this.userName = userName;
        this.userPass = userPass;
        this.userAvatar = userAvatar;
        this.email = email;
        this.colorFavorite = "";
        this.fullName = fullName;
        android_2_func = new android_2_func();
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            this.userID = fAuth.getCurrentUser().getUid();
        }
    }

    public void setColorFavorite(String color) {
        this.colorFavorite = color;
    }

    public String getColorFavorite() {
        return this.colorFavorite;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTask() {
        return this.task;
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
                                addUserInformation(fAuth.getUid(), user);//Add information of new user sign up
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            SwitchActivity.goToSignUpSuccess(context);

                        } else {
                            SwitchActivity.goToSignUpError(context);
                        }

                        //Remove
                        Activity activity = (Activity) context;
                        activity.finish();
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
                            ID_USER_LOGGED_IN = fAuth.getUid();
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
        if (fAuth.getCurrentUser() != null){
            ID_USER_LOGGED_IN = fAuth.getUid();
            return true;
        }
        return false;
    }

    public JSONObject toJSON(Users user) throws JSONException {
        JSONObject json = new JSONObject();
        String color = user.getColorFavorite();
        if (color != null || color.equals("")){
            color = com.example.aspire.android_2_func.getRandomColor();
        }

        json.put("userName", user.getUserName());
        json.put("userID", user.getUserID());
        json.put("fullName", user.getFullName());
        json.put("colorFavorite",  color);
        json.put("userAvatar", com.example.aspire.android_2_func.getImgAvatar());
        return json;
    }
}
