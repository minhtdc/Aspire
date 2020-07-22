package com.example.aspire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class StartingActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.starting_layout);

    Button btn_login = findViewById(R.id.btn_login);
    Button btn_sign = (Button) findViewById(R.id.btn_signUp);

    btn_login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Starting a new Intent
        Intent nextScreen = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(nextScreen);
      }
    });

    btn_sign.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Starting a new Intent
        Intent nextScreen = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(nextScreen);
      }
    });
  }
}