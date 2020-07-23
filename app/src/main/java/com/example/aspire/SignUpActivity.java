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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText edt_fullname, edt_username, edt_password, edt_re_password, edt_email;
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private Dialog epicDialog;

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
                    final String
                            fullName = (String) edt_fullname.getText().toString(),
                            email = (String) edt_email.getText().toString(),
                            userName = (String) edt_username.getText().toString(),
                            password = (String) edt_password.getText().toString(),
                            re_password = (String) edt_re_password.getText().toString();

                    // Check validate data submit
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                showNotification("Đăng ký thành công", String.format("Bạn đã đăng ký thành công với email là %s, hãy thử đăng nhập ngay nào!", email), true);
                            } else {
                                showNotification("Đăng ký không thành công", "Có lẻ tài khoản của bạn đã trùng với ai đó hoặc có vấn đề về máy chủ", false);
                            }
                        }
                    });
                } catch (Exception ex) {
                    Toast.makeText(SignUpActivity.this, "Vui lòng nhập đúng dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showNotification(String title, String desNotification, boolean isSuccess) {
        epicDialog.setContentView(R.layout.epic_popup_negative);
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Set content
        CardView card = epicDialog.findViewById(R.id.card_notification);
        TextView txt_title = epicDialog.findViewById(R.id.txt_title);
        TextView txt_desNotification = epicDialog.findViewById(R.id.txt_desNotification);
        ImageView img_notification = epicDialog.findViewById(R.id.img_notification);
        Button btn_success = epicDialog.findViewById(R.id.btn_success);
        String colorIsSuccess = "";

        //Set config notification
        if (isSuccess) {
            colorIsSuccess = "#4CAF50";
            img_notification.setImageResource(R.mipmap.icon_success);
            btn_success.setText("Đăng nhập");

            btn_success.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Starting a new Intent
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            });
        } else {
            colorIsSuccess = "#FF675C";
            img_notification.setImageResource(R.mipmap.icon_error);
            btn_success.setText("Đã hiểu");

            btn_success.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    epicDialog.dismiss();
                }
            });
        }

        txt_title.setText(title);
        txt_desNotification.setText(desNotification);
        card.setCardBackgroundColor(Color.parseColor(colorIsSuccess));
        epicDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), StartingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}