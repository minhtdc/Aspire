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

import com.example.aspire.LoginActivity;
import com.example.aspire.R;
import com.example.aspire.SwitchActivity;
import com.example.aspire.android_2_func;

public class InputFullNameActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txt_inToolbar;
    ImageButton imgBtn_inToolbar;

    Button btn_next;
    EditText edt_firstName, edt_lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_full_name_layout);

        //Set information in toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Tên");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToStart(InputFullNameActivity.this);
                finish();
            }
        });

        edt_firstName = findViewById(R.id.edt_firstname);
        edt_lastName = findViewById(R.id.edt_lastname);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = edt_firstName.getText().toString();
                String lastName = edt_lastName.getText().toString();

                //Validate input and go to input username activity
                if (android_2_func.isName(firstName + " " + lastName) && !firstName.equals("") && !lastName.equals("")) {
                    SwitchActivity.goToInputUsername(InputFullNameActivity.this, firstName + " " + lastName);
                    finish();
                } else {
                    Toast.makeText(InputFullNameActivity.this, "Vui lòng nhập đúng định dạng tên", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}