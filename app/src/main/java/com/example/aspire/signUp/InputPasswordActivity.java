package com.example.aspire.signUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aspire.R;
import com.example.aspire.SignUpActivity;
import com.example.aspire.SwitchActivity;
import com.example.aspire.data_models.Users;

public class InputPasswordActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txt_inToolbar;
    ImageButton imgBtn_inToolbar;
    Button btn_signUp;
    Dialog epicDialog = null;

    EditText edt_password, edt_re_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_password_layout);

        // Get value
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        final String fullName = bundle.getString("fullName");
        final String userName = bundle.getString("userName");
        final String email = bundle.getString("email");

        //Set information in toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Nhập Email");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToInputUsername(InputPasswordActivity.this, fullName);
                finish();
            }
        });

        edt_password = findViewById(R.id.edt_password);
        edt_re_password = findViewById(R.id.edt_re_password);

        btn_signUp = findViewById(R.id.btn_signUp);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = edt_password.getText().toString(),
                        re_password = edt_re_password.getText().toString();

                if (password.equals(re_password) && !password.equals("")) {
                    epicDialog = new Dialog(InputPasswordActivity.this);
                    Users userAuth = new Users(userName, password, email, fullName);
                    try {
                        userAuth.createUserWithEmailAndPassword(epicDialog, InputPasswordActivity.this, userAuth);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(InputPasswordActivity.this, "Vui lòng nhập đúng mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}