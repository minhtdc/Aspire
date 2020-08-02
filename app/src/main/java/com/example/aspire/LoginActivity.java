package com.example.aspire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aspire.data_models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    public String userLogin;
    private EditText edt_email, edt_password;
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private Dialog epicDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        this.edt_email = findViewById(R.id.edt_email);
        this.edt_password = findViewById(R.id.edt_password);
        epicDialog = new Dialog(this);

        //get view frm layout
        Button btnLogin;
        btnLogin = findViewById(R.id.btn_login);





        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users user = new Users();
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();
                //Check validate form login
                if (email.equals("") && password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đủ và đúng thông tin đăng nhập!", Toast.LENGTH_SHORT).show();
                } else {
                    if (android_2_func.isValidEmailId(email)) {
                        try {
                            // Do login with email and password
                            user.loginUserWithEmailAndPassword(email, password, epicDialog, LoginActivity.this);
                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, "Lỗi đăng nhập!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Email nhập không đúng như định dạng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), StartingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}