package com.example.aspire;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterMemberManage;
import com.example.aspire.data_models.MemberManage;
import com.example.aspire.data_models.Users;

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


            // get view from layout
            ImageView imgUser = (ImageView) findViewById(R.id.userAvatar);
           // Button btnDelete = (Button) findViewById(R.id.btnDelete);
            TextView txtName = (TextView) findViewById(R.id.txtName);

            listMember = new ArrayList<MemberManage>();

        MemberManage memberManage1 = new MemberManage("avatar tv1","huynhoc");
        listMember.add(memberManage1);
        MemberManage memberManage2 = new MemberManage("avatar tv2","duynhoc");
        listMember.add(memberManage2);
            adapter = new AdapterMemberManage(this, R.layout.listview_members_manage_layout, listMember);
        listMembersManage.setAdapter(adapter);



    }
}
