package com.example.aspire;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.data_models.Newfeed;
import com.example.aspire.data_models.Users;

import java.util.ArrayList;

public class NewFeedActivity extends AppCompatActivity {

    private AdapterNewfeed adapter;
    private ArrayList<Newfeed> listNew;
    ListView listNewFeed;
    Button btnSearch;
    EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_feed_layout);
        setTitle("Trang chủ");

        listNewFeed = findViewById(R.id.listNew);

        listNew = new ArrayList<Newfeed>();

        adapter = new AdapterNewfeed(this, R.layout.listview_newfeed_layout, listNew);

        listNewFeed.setAdapter(adapter);

//        Toast.makeText(this, Users.userID, Toast.LENGTH_LONG).show();

        btnSearch =findViewById(R.id.btnSearch);
        editSearch = findViewById(R.id.edtSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = new Users();
                if(users.isLogged()){
                    editSearch.setText("Chào mừng" + users.getUserID());
                }
            }
        });
        }
    }
