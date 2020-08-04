package com.example.aspire;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterComments;
import com.example.aspire.data_models.Comments;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {
    private AdapterComments adapterComments;
    private ArrayList<Comments> listComments;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_layout);
        //get view from layout
        listView = findViewById(R.id.listViewComments);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //test add dữ liệu
        listComments = new ArrayList<Comments>();
        Comments cmt1 = new Comments("Huyen Nguyen", "Hi, Min Min. Hi, Nhu Tran :) ");
        listComments.add(cmt1);
        Comments cmt2 = new Comments("Minh Tran :v", "Hi, Huyen Chinh :)");
        listComments.add(cmt2);
        Comments cmt3 = new Comments("Nhu Nguyen :)", "Hi, Huyen Chinh :)");
        listComments.add(cmt3);
        adapterComments = new AdapterComments(this, R.layout.listview_comment_layout, listComments);
        listView.setAdapter(adapterComments);
    }
}