package com.example.aspire;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterMemberManage;
import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MembersActivity extends AppCompatActivity {
    private AdapterMemberManage adapter;
    private ArrayList<Users> listMember;
    ListView listMembersManage;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.members_layout);
        setTitle("Thành viên nhóm");
        listMembersManage = findViewById(R.id.listMember);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // get view from layout
        ImageView imgUser = (ImageView) findViewById(R.id.userAvatar);
        // Button btnXoa = (Button) findViewById(R.id.btnXoa);
        TextView edtUserName = (TextView) findViewById(R.id.txtName);

        listMember = new ArrayList<Users>();

        adapter = new AdapterMemberManage(this, R.layout.listview_members_manage_layout, listMember);
        listMembersManage.setAdapter(adapter);

        //Lấy groupID từ màn AdapterNewFeed thông qua intent
        intent = AdapterNewfeed.intent;
        String groupID = intent.getBundleExtra("group").getString("groupID");

        //hiện thông tin member của nhóm từ db lên listview
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference listMember = database.getReference("groups").child(groupID).child("listMembers");
        final Users user = new Users(); // để gán fullname
        listMember.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                for(final DataSnapshot data : snapshot.getChildren()){

                    String key = data.getKey();
                    DatabaseReference getFullName = FirebaseDatabase.getInstance().getReference("users").child(key).child("fullName");
                    getFullName.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String fullName = snapshot.getValue(String.class);
                            user.setFullName(fullName);
                            adapter.add(user);
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
