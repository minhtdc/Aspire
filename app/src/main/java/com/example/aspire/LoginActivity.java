package com.example.aspire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aspire.data_models.Users;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_email, edt_password;
    private Dialog epicDialog;
    private TextView txt_goToSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        this.edt_email = findViewById(R.id.edt_email);
        this.edt_password = findViewById(R.id.edt_password);
        this.txt_goToSignUp = findViewById(R.id.txt_goToSignUp);
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
                    }else{
                        Toast.makeText(LoginActivity.this, "Email nhập không đúng như định dạng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Get event go to sign up
        this.txt_goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToSignUp(LoginActivity.this);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}