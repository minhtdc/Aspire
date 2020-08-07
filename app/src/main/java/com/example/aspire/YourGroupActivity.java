package com.example.aspire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.data_models.Groups;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class YourGroupActivity extends AppCompatActivity {
    ListView listViewGroup;
    private ArrayList<Groups> listGroup;
    Button addGroup;
    private AdapterNewfeed adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_manager_layout);
        setTitle("Nhóm bạn đã tham gia");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        addGroup = findViewById(R.id.btnAddGroup);
        listViewGroup = findViewById(R.id.listGroupManage);
        listGroup = new ArrayList<Groups>();

        //làm mất nút thêm ở màn hình chỉ xem thành viên
        addGroup.setVisibility(View.GONE);

        adapter = new AdapterNewfeed(this, R.layout.listview_newfeed_layout, listGroup);
        listViewGroup.setAdapter(adapter);

        final Users user = new Users(); // để lấu userid so sánh
        // đưa dữ liệu từ db lên listview
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myGroups = database.getReference("groups");
        myGroups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot1) {
                adapter.clear();
                for (final DataSnapshot data1 : snapshot1.getChildren()) {
                    String idGroup = data1.getKey();
                    DatabaseReference getMembersID = FirebaseDatabase.getInstance().getReference("groups").child(idGroup).child("listMembers");
                    //lấy id members của các nhóm ra
                    getMembersID.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            for (DataSnapshot data2 : snapshot2.getChildren()) {
                                String memberID = data2.getKey();
                                //nếu memberID của nhóm bằng với memberID trong nhóm thì hiển thị lên
                                if(memberID.equals(user.getUserID())){
                                    Groups group = data1.getValue(Groups.class);
                                    adapter.add(group);
                                }
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
