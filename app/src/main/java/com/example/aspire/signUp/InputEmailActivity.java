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

public class InputEmailActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txt_inToolbar;
    ImageButton imgBtn_inToolbar;

    EditText edt_email;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_email_layout);

        // Get value
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        final String fullName = bundle.getString("fullName");
        final String userName = bundle.getString("userName");

        //Set information in toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Nhập Email");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToInputUsername(InputEmailActivity.this, fullName);
                finish();
            }
        });

        edt_email = findViewById(R.id.edt_email);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString();
                if (android_2_func.isValidEmailId(email)) {
                    SwitchActivity.goToInputPassword(InputEmailActivity.this, fullName, userName, email);
                    finish();
                } else {
                    Toast.makeText(InputEmailActivity.this, "Vui lòng nhập đúng định dạng email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}