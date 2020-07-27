package com.example.aspire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aspire.data_models.Groups;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;

import java.security.acl.Group;

public class CreateGroupActivity extends AppCompatActivity {
    public Groups groups;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstace;
    private String Id;

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

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");

            }
        });
    }
}
