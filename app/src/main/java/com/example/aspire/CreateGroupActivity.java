package com.example.aspire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aspire.data_models.Groups;

import org.json.JSONException;

public class CreateGroupActivity extends AppCompatActivity {
    public  Groups groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group_layout);

        Button btnCreateGroup;
        final EditText edtGroupName;

        btnCreateGroup = findViewById(R.id.btnCreateGroup);
        edtGroupName = (EditText) findViewById(R.id.edtGroupName);
        setTitle("Tạo nhóm");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groups = new Groups("admin","member","minhh");
                try {
                    groups.addGroupToDatabase("10", groups);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
