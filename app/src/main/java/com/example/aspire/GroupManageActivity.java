package com.example.aspire;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterMemberManage;
import com.example.aspire.data_models.MemberManage;

import java.util.ArrayList;

public class GroupManageActivity extends AppCompatActivity {
        private AdapterMemberManage adapter;
        private ArrayList<MemberManage> listMember;
        ListView listGroupManage;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.group_manager_layout);
            setTitle("Danh sách nhóm");
            listGroupManage = findViewById(R.id.listGroup);


            // get view from layout
            ImageView imgUser = (ImageView) findViewById(R.id.userAvatar);
            // Button btnXoa = (Button) findViewById(R.id.btnXoa);
            TextView txtName = (TextView) findViewById(R.id.txtName);

            listMember = new ArrayList<MemberManage>();

            MemberManage memberManage1 = new MemberManage("avatar nhom 1","hihi");
            listMember.add(memberManage1);
            MemberManage memberManage2 = new MemberManage("avatar nhom2","liuliu");
            listMember.add(memberManage2);
            adapter = new AdapterMemberManage(this, R.layout.listview_members_manage_layout, listMember);
            listGroupManage.setAdapter(adapter);
    }
}
