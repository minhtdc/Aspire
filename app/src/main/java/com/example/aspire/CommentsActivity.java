package com.example.aspire;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterComments;
import com.example.aspire.data_models.Comments;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {
    ListView listViewComments;
    private ArrayList<Comments> listComment;
    private AdapterComments adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_layout);

        String contentCommentTest = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters";

        listComment = new ArrayList<Comments>();
        listComment.add(new Comments("User test", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));
        listComment.add(new Comments("User test 1", contentCommentTest));
        listComment.add(new Comments("User test 2", "Lorem Ipsum is simply dummy text "));
        listComment.add(new Comments("User test 4", "The generated"));
        listComment.add(new Comments("User test 5", "Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
        listComment.add(new Comments("User test 5", "Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
        listComment.add(new Comments("User test 5", "Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
        listComment.add(new Comments("User test 5", "Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
        listComment.add(new Comments("User test 5", "Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
        listComment.add(new Comments("User test 5", "Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
        listComment.add(new Comments("User test 5", "Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));

        //get view from layout
        listViewComments = findViewById(R.id.listViewComments);
        adapter = new AdapterComments(this, R.layout.comment_item, listComment);
        listViewComments.setAdapter(adapter);
    }
}