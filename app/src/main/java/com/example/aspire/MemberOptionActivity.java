package com.example.aspire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.aspire.adapter.AdapterMemberOption;
import com.example.aspire.data_models.Users;

import java.util.ArrayList;

public class MemberOptionActivity extends AppCompatActivity {
    private AdapterMemberOption adapter;
    private ArrayList<Users> listMembers;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_option_layout);
        setTitle("Duyệt thành viên");
        listView = findViewById(R.id.listMember);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        listMembers = new ArrayList<Users>();

        Users users1 = new Users("id","name","pass","avt");
        listMembers.add(users1);
        Users users2 = new Users("id2","name2","pass2","avt2");
        listMembers.add(users2);


        adapter = new AdapterMemberOption(this, R.layout.list_member_option_layout, listMembers);
        //adapter = new ArrayAdapter<NhanSu>(this,android.R.layout.simple_list_item_1, arrNhanSu);
        listView.setAdapter(adapter);

    }
}
