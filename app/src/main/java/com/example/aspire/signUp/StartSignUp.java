package com.example.aspire.signUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aspire.R;
import com.example.aspire.SwitchActivity;

public class StartSignUp extends AppCompatActivity {
    Toolbar toolbar;
    TextView txt_inToolbar, txt_haveAccount;
    ImageButton imgBtn_inToolbar;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_sign_up_layout);

        //Set information in toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Tạo tài khoản");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToStart(StartSignUp.this);
                finish();
            }
        });

        txt_haveAccount = findViewById(R.id.txt_haveAccount);
        btn_next = findViewById(R.id.btn_next);

        txt_haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToLogin(StartSignUp.this);
                finish();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToInputFullName(StartSignUp.this);
                finish();
            }
        });
    }
}