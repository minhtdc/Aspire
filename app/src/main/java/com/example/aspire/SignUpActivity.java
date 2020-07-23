package com.example.aspire;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
  EditText edt_fullname, edt_username, edt_password, edt_re_password, edt_email;
  FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
  Button btn_signUp;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sign_up_layout);

    //Get elements
    btn_signUp = findViewById(R.id.btn_signUp);
    edt_fullname = findViewById(R.id.edt_fullname);
    edt_email = findViewById(R.id.edt_email);
    edt_username = findViewById(R.id.edt_username);
    edt_password = findViewById(R.id.edt_password);
    edt_re_password = findViewById(R.id.edt_re_password);

    //Set event click for button sign up
    btn_signUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Get value form input
        String
                fullName = (String) edt_fullname.getText().toString(),
                email = (String) edt_email.getText().toString(),
                userName = (String) edt_username.getText().toString(),
                password = (String) edt_password.getText().toString(),
                re_password = (String) edt_re_password.getText().toString();


        // Check validate data submit

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