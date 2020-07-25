package com.example.aspire.data_models;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aspire.Notification;
import com.example.aspire.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.TimeUnit;

public class Users {
    private String userID, userName, userPass, userAvata;
    public static boolean result;
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
}
