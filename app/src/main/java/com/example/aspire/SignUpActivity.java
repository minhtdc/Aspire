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
import android.widget.Toast;

import com.example.aspire.data_models.Users;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText edt_fullname, edt_username, edt_password, edt_re_password, edt_email;
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    Dialog epicDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        //Get elements
        Button btn_signUp = findViewById(R.id.btn_signUp);
        this.edt_fullname = findViewById(R.id.edt_fullname);
        this.edt_email = findViewById(R.id.edt_email);
        this.edt_username = findViewById(R.id.edt_username);
        this.edt_password = findViewById(R.id.edt_password);
        this.edt_re_password = findViewById(R.id.edt_re_password);
        epicDialog = new Dialog(this);

        //Set event click for button sign up
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Get value form input
                    boolean result = false;
                    final String
                            fullName = (String) edt_fullname.getText().toString(),
                            email = (String) edt_email.getText().toString(),
                            userName = (String) edt_username.getText().toString(),
                            password = (String) edt_password.getText().toString(),
                            re_password = (String) edt_re_password.getText().toString();
                    // Check validate data submit
                    if (fullName.equals("") || email.equals("") || userName.equals("") || password.equals("") || re_password.equals("")) {
                        Toast.makeText(SignUpActivity.this, "Vui lòng nhập đúng dữ liệu", Toast.LENGTH_SHORT).show();
                    } else {
                        if (android_2_func.isValidEmailId(email)) {
                            if (!password.equals(re_password)) {
                                Notification.error(epicDialog, SignUpActivity.this,
                                        "Đăng ký không thành công",
                                        "Mật khẩu xác nhận của bạn không đúng như trước!");
                            } else {
                                Users userAuth = new Users(userName, password, email, fullName);
                                userAuth.createUserWithEmailAndPassword(epicDialog, SignUpActivity.this, userAuth);
                            }
                        } else {
                            Toast.makeText(SignUpActivity.this, "Email không đúng", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception ex) {
                    Toast.makeText(SignUpActivity.this, "Lỗi trong quá trình đăng ký", Toast.LENGTH_SHORT).show();
                }
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