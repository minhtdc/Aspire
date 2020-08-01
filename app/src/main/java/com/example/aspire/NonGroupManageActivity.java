package com.example.aspire;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NonGroupManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_group_manage_layout);

        setTitle("Không có nhóm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
