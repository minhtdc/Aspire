package com.example.aspire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.data_models.Newfeed;

import java.util.ArrayList;

public class NewFeedActivity extends AppCompatActivity {

    private AdapterNewfeed adapter;
    private ArrayList<Newfeed> listNew;
    ListView listNewFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_feed_layout);
        setTitle("Trang chủ");

        listNewFeed = findViewById(R.id.listNew);

        listNew = new ArrayList<Newfeed>();

        adapter = new AdapterNewfeed(this, R.layout.listview_newfeed_layout, listNew);

        Newfeed newfeed1 = new Newfeed("avatar nha", "bi le", "nva");
        Newfeed newfeed2 = new Newfeed("avatar ne", "huy le", "hvt");
        listNew.add(newfeed1);
        listNew.add(newfeed2);
        listNewFeed.setAdapter(adapter);



    }
}
