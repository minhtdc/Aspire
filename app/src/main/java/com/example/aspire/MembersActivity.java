package com.example.aspire;

import android.os.Bundle;
import android.view.View;
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
            // get view from layout
            ImageView imgUser = (ImageView) findViewById(R.id.userAvatar);
            final Button btnDelete = (Button) findViewById(R.id.btnDelete);
            TextView edtUserName = (TextView) findViewById(R.id.txtName);

            listMember = new ArrayList<MemberManage>();

        MemberManage m1 = new MemberManage("ml", "ssds");
        MemberManage m2 = new MemberManage("xaax", "ssdqwdqeqs");

        listMember.add(m1);
        listMember.add(m2);
            adapter = new AdapterMemberManage(this, R.layout.listview_members_manage_layout, listMember);
        listMembersManage.setAdapter(adapter);

    }
}
