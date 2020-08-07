package com.example.aspire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.data_models.Groups;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupManageActivity extends AppCompatActivity {
    ListView listViewGroup;
    private ArrayList<Groups> listGroup;
    Button addGroup;
    private AdapterNewfeed adapter;

    //Author: Tran Minh Phuc 06-08-2020
    private Toolbar toolbar;
    ImageButton btn_backInActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_manager_layout);

        //Set information in toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn_backInActionbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        btn_backInActionbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToPersonPage(GroupManageActivity.this);
                finish();
            }
        });

        addGroup = findViewById(R.id.btnAddGroup);
        listViewGroup = findViewById(R.id.listGroupManage);
        listGroup = new ArrayList<Groups>();

        adapter = new AdapterNewfeed(this, R.layout.listview_newfeed_layout, listGroup);
        listViewGroup.setAdapter(adapter);

        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupManageActivity.this, CreateGroupActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        final Users user = new Users(); // để lấu userid so sánh
        // đưa dữ liệu từ db lên listview
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myGroups = database.getReference("groups");
        myGroups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot1) {
                adapter.clear();
                for (final DataSnapshot data : snapshot1.getChildren()) {
                    String idGroup = data.getKey();
                    DatabaseReference getAdminID = FirebaseDatabase.getInstance().getReference("groups").child(idGroup).child("adminID");
                    //lấy id admin của các nhóm ra
                    getAdminID.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            String adminID = snapshot2.getValue(String.class);

                            //nếu adminId của nhóm bằng với user thì hiển thị lên list view
                            if(adminID.equals(user.getUserID())){
                                Groups group = data.getValue(Groups.class);
                                adapter.add(group);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
