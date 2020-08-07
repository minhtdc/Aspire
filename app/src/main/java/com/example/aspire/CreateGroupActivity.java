package com.example.aspire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aspire.data_models.Groups;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;

import java.security.acl.Group;
import java.util.ArrayList;

public class CreateGroupActivity extends AppCompatActivity {
    public Groups groups;
    private DatabaseReference mDatabase;
    private String Id;

    //Author: Tran Minh Phuc 06-08-2020
    Toolbar toolbar;
    TextView txt_inToolbar;
    ImageButton imgBtn_inToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group_layout);

        Button btnCreateGroup;
        final EditText edtGroupName;
        final EditText edtGroupInfo;

        //Set information in toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Tạo nhóm mới");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToGroupManage(CreateGroupActivity.this);
                finish();
            }
        });


        btnCreateGroup = findViewById(R.id.btnCreateGroup);
        edtGroupName = (EditText) findViewById(R.id.edtGroupName);
        edtGroupInfo = (EditText) findViewById(R.id.edtGroupInfo);

        btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtGroupName.getText().toString() != "" && edtGroupInfo.getText().toString() != "") {
                    Groups group = new Groups();
                    //tạo ra id group không trùng
                    mDatabase = FirebaseDatabase.getInstance().getReference("groups");
                    String groupID = mDatabase.push().getKey();

                    group.setGroupID(groupID);
                    group.setGroupName(edtGroupName.getText().toString());
                    group.setGroupInfo(edtGroupInfo.getText().toString());
                    Users user = new Users();
                    group.setAdminID(user.getUserID());
                    //group.setAdminId("admin");
                    try {
                        group.addGroupToDatabase(group);
                        //đưa admin tahnfh thành viên của nhóm để có thể vào nhóm
                        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("groups").child(groupID).child("listMembers").child(user.getUserID());
                        myRef.setValue("admin");
                        Toast.makeText(CreateGroupActivity.this, "Tạo nhóm thành công!", Toast.LENGTH_SHORT).show();
                        SwitchActivity.goToNewFeed(CreateGroupActivity.this);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(CreateGroupActivity.this, "Tên nhóm không được để trống!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
