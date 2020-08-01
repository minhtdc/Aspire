package com.example.aspire;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.data_models.Newfeed;
import com.example.aspire.data_models.Users;
import com.example.aspire.data_models.Groups;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewFeedActivity extends AppCompatActivity {

    private AdapterNewfeed adapter;

    private ArrayList<Groups> listGroup;
    ListView listViewGroup;
    Button btnSearch;
    EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_feed_layout);
        setTitle("Trang chủ");

        listViewGroup = findViewById(R.id.listNew);
        listGroup = new ArrayList<Groups>();

        adapter = new AdapterNewfeed(this, R.layout.listview_newfeed_layout, listGroup);
        listViewGroup.setAdapter(adapter);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myGroups = database.getReference("groups");
        myGroups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    String key = data.getKey();
                    Groups group = data.getValue(Groups.class);
                    group.setGroupID(key);
                    Toast.makeText(NewFeedActivity.this, key, Toast.LENGTH_SHORT).show();
                    adapter.add(group);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Toast.makeText(this, Users.userID, Toast.LENGTH_LONG).show();
        btnSearch = findViewById(R.id.btnSearch);
        editSearch = findViewById(R.id.edtSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = new Users();
                if (users.isLogged()) {
                    editSearch.setText("Chào mừng" + users.getUserID());
                }
            }
        });
    }

}