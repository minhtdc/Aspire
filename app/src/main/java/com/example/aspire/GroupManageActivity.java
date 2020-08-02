package com.example.aspire;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterMemberManage;
import com.example.aspire.data_models.MemberManage;
import com.example.aspire.data_models.Requests;

import java.util.ArrayList;

public class GroupManageActivity extends AppCompatActivity {
        private AdapterMemberManage adapter;
        private ArrayList<Requests> listMember;
        ListView listGroupManage;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.group_manager_layout);
            setTitle("Danh sách nhóm");
            listGroupManage = findViewById(R.id.listGroup);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            // get view from layout
            ImageView imgUser = (ImageView) findViewById(R.id.userAvatar);
            // Button btnXoa = (Button) findViewById(R.id.btnXoa);
            TextView edtUserName = (TextView) findViewById(R.id.txtName);

            listMember = new ArrayList<Requests>();
            adapter = new AdapterMemberManage(this, R.layout.listview_members_manage_layout, listMember);
            listGroupManage.setAdapter(adapter);
    }
}
