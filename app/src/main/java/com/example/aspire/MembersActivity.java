package com.example.aspire;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterMemberManage;
import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.data_models.MemberManage;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class MembersActivity extends AppCompatActivity {
    private AdapterMemberManage adapter;
    private ArrayList<MemberManage> listMember;
    ListView listMembersManage;
    Intent intent;
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

            listMember = new ArrayList<MemberManage>();

            adapter = new AdapterMemberManage(this, R.layout.listview_members_manage_layout, listMember);
            listMembersManage.setAdapter(adapter);

            intent = AdapterNewfeed.intent;
            String idGroup = (intent.getBundleExtra("group")).getString("groupID");

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference MyRef = database.getReference("groups").child(idGroup).child("listMembers");
            MyRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    adapter.clear();
                    for(DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        String key = dataSnapshot.getKey();
                        Users users = dataSnapshot.getValue(Users.class);
                        users.setUserID(key);
                        adapter.add(users);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("", "Failed to show member", error.toException());
                }
            });


    }
}
