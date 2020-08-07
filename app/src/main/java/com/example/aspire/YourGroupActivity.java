package com.example.aspire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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

public class YourGroupActivity extends AppCompatActivity {
    ListView listViewGroup;
    private ArrayList<Groups> listGroup;
    Button addGroup;
    private AdapterNewfeed adapter;

    //Author: Tran Minh Phuc 06-08-2020
    Toolbar toolbar;
    TextView txt_inToolbar;
    ImageButton imgBtn_inToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_manager_layout);

        addGroup = findViewById(R.id.btnAddGroup);
        listViewGroup = findViewById(R.id.listGroupManage);
        listGroup = new ArrayList<Groups>();

        /*
        * Author: Tran Minh Phuc 06-08-2020
        * Set information in toolbar
        */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Nhóm bạn đã tham gia");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToPersonPage(YourGroupActivity.this);
                finish();
            }
        });

        //làm mất nút thêm ửo màn hình chỉ xem thành viên
        addGroup.setVisibility(View.GONE);

        adapter = new AdapterNewfeed(this, R.layout.listview_newfeed_layout, listGroup);
        listViewGroup.setAdapter(adapter);
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
