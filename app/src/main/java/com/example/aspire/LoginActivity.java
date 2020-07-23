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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_email, edt_password;
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private Dialog epicDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        this.edt_email = findViewById(R.id.edt_email);
        this.edt_password = findViewById(R.id.edt_password);

        //get view frm layout
        Button btnLogin;
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String email = edt_email.getText().toString();
                    String password = edt_password.getText().toString();
                    mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener() {
                        @NonNull
                        @Override
                        protected Object clone() throws CloneNotSupportedException {
                            Toast.makeText(LoginActivity.this, "Dang ddăng nhập!", Toast.LENGTH_SHORT).show();
                            return super.clone();
                        }

                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), NewFeedActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                startActivity(intent);
                                finish();
                            } else {
                                showNotification("Đăng nhập không thành công", "Hãy thử đăng nhập lại, chắc là bạn đã nhập sai gì đó!", false);
                            }
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đủ và đúng thông tin đăng nhập!", Toast.LENGTH_SHORT).show();
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

}