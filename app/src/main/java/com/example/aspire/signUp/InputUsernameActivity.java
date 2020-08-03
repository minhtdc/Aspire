package com.example.aspire.signUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aspire.R;
import com.example.aspire.SwitchActivity;
import com.example.aspire.android_2_func;

public class InputUsernameActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txt_inToolbar;
    ImageButton imgBtn_inToolbar;

    EditText edt_username;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_username_layout);

        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        final String fullName = bundle.getString("fullName");

        //Set information in toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Username");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToInputFullName(InputUsernameActivity.this);
                finish();
            }
        });


        edt_username = findViewById(R.id.edt_username);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edt_username.getText().toString();
                if (android_2_func.isUsername(userName)) {
                    SwitchActivity.goToInputEmail(InputUsernameActivity.this, fullName, userName);
                    finish();
                } else {
                    Toast.makeText(InputUsernameActivity.this, "Vui lòng nhập đúng định dạng username", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}