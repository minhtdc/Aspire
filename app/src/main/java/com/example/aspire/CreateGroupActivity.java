package com.example.aspire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
=======
import android.widget.Toast;
>>>>>>> Minh-Nguyễn

import com.example.aspire.data_models.Groups;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;

import java.security.acl.Group;
<<<<<<< HEAD

public class CreateGroupActivity extends AppCompatActivity {
    public Groups groups;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstace;
=======
import java.util.ArrayList;

public class CreateGroupActivity extends AppCompatActivity {
    public Groups groups;
    private DatabaseReference mDatabase;
>>>>>>> Minh-Nguyễn
    private String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group_layout);

        Button btnCreateGroup;
        final EditText edtGroupName;
<<<<<<< HEAD

        btnCreateGroup = findViewById(R.id.btnCreateGroup);
        edtGroupName = (EditText) findViewById(R.id.edtGroupName);
=======
        final EditText edtGroupInfo;

        btnCreateGroup = findViewById(R.id.btnCreateGroup);
        edtGroupName = (EditText) findViewById(R.id.edtGroupName);
        edtGroupInfo = (EditText) findViewById(R.id.edtGroupInfo);
>>>>>>> Minh-Nguyễn
        setTitle("Tạo nhóm");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");

=======
                if (edtGroupName.getText().toString() != "" && edtGroupInfo.getText().toString() != "") {
                    Groups group = new Groups();
                    //tạo ra id group không trùng
                    mDatabase = FirebaseDatabase.getInstance().getReference("groups");
                    String groupID = mDatabase.push().getKey();

                    group.setGroupID(groupID);
                    group.setGroupName(edtGroupName.getText().toString());
                    group.setGroupInfo(edtGroupInfo.getText().toString());
                    Users user = new Users();
                    group.setAdminId(user.getUserID());
                    //group.setAdminId("admin");
                    try {
                        group.addGroupToDatabase(group);
                        Toast.makeText(CreateGroupActivity.this, "Tạo nhóm thành công!", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(CreateGroupActivity.this, "Tên nhóm không được để trống!", Toast.LENGTH_SHORT).show();
                }
>>>>>>> Minh-Nguyễn
            }
        });
    }
}
