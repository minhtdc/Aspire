package com.example.aspire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    private Toolbar toolbar;
    private TextView txt_inToolbar;
    private ImageButton imgBtn_inToolbar;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.members_layout);

        //Lấy groupID từ màn AdapterNewFeed thông qua intent
        //Extract the data…
        bundle = getIntent().getExtras();
        final String groupID = bundle.getString("groupID");
        final String idUser = Users.ID_USER_LOGGED_IN;

        //Set information in toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Danh sách các thành viên");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToListPost(MembersActivity.this, groupID);
                finish();
            }
        });

        listMembersManage = findViewById(R.id.listMember);
        listMember = new ArrayList<Users>();
        adapter = new AdapterMemberManage(this, R.layout.listview_members_manage_layout, listMember, groupID, idUser);
        listMembersManage.setAdapter(adapter);

        //hiện thông tin member của nhóm từ db lên listview
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference listMember = database.getReference("groups").child(groupID).child("listMembers");
        listMember.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                for (final DataSnapshot data : snapshot.getChildren()) {
                    final String key = data.getKey();
                    final String task = data.getValue().toString();

                    DatabaseReference getFullName = FirebaseDatabase.getInstance().getReference("users").child(key);
                    getFullName.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot_user) {
                            Users user = new Users();
                            user.setTask(task);
                            user.setUserID(key);
                            for (DataSnapshot data_user : snapshot_user.getChildren()) {
                                if (data_user.getKey().equals("fullName")) {
                                    user.setFullName(data_user.getValue().toString());
                                } else {
                                    if (data_user.getKey().equals("userAvatar")) {
                                        user.setUserAvatar(data_user.getValue().toString());
                                    } else {
                                        if (data_user.getKey().equals("userName")) {
                                            user.setUserName(data_user.getValue().toString());
                                        } else {
                                            if (data_user.getKey().equals("colorFavorite")) {
                                                if (data_user.getValue().toString().equals("") || data_user.getValue() == null) {
                                                    user.setColorFavorite(android_2_func.getRandomColor());
                                                } else {
                                                    user.setColorFavorite(data_user.getValue().toString());
                                                }
                                            } else {
                                                if (data_user.getKey().equals("userAvatar")) {
                                                    user.setUserAvatar(data_user.getValue().toString());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
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
