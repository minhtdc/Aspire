package com.example.aspire;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterMemberManage;
import com.example.aspire.data_models.MemberManage;
import com.example.aspire.data_models.Requests;
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
            TextView edtUserName = (TextView) findViewById(R.id.txtName);

            listMember = new ArrayList<MemberManage>();

            adapter = new AdapterMemberManage(this, R.layout.listview_members_manage_layout, listMember);
        listMembersManage.setAdapter(adapter);
        //lấy đối tượng FirebaseDatabase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Kết nối tới node có tên là contacts (node này do ta định nghĩa trong CSDL Firebase)
        DatabaseReference myRef = database.getReference("requests");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                //vòng lặp để lấy dữ liệu khi có sự thay đổi trên Firebase
                for (DataSnapshot data : snapshot.getChildren()) {
                    //lấy key của dữ liệu
                    String key = data.getKey();
                    //lấy giá trị của key (nội dung)
                    MemberManage memberManage = data.getValue(MemberManage.class);
                    memberManage.setAdminID(key);
                    adapter.add(memberManage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



    }
}
