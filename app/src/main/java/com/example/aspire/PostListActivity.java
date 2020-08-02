package com.example.aspire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.MyPostListAdapter;
import com.example.aspire.data_models.Post;

import java.util.ArrayList;

public class PostListActivity extends AppCompatActivity {

    de.hdodenhof.circleimageview.CircleImageView userAvtCircle;
    TextView userName, userPosition, userTimePost, userTitlePost, userContentPost, userViewPost, userCountCommentPost;
    ListView listPost;
    private MyPostListAdapter adapter;
    private ArrayList<Post> listPostMember;
    Button cmt;
    Button gotoMember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_list_layout);

        listPost = findViewById(R.id.listPost);
        cmt = findViewById(R.id.btnComment);
        gotoMember = findViewById(R.id.btnGoToMember);


        listPostMember = new ArrayList<Post>();
        Post post1 = new Post("Nhu Tran", "Thanh vien", "1 gio truoc", "Tieu de test 1",
                "Noi dung testb 1 ", "2 luot xem", "4 binh luan");
        Post post2 = new Post("Nhu Tran", "Thanh vien",
                "1 gio truoc", "Tieu de test 1", "Noi dung testb 1 ", "2 luot xem",
                "4 binh luan");
        listPostMember.add(post1);
        listPostMember.add(post2);
        adapter = new MyPostListAdapter(this, R.layout.post_detail_member_layout, listPostMember);

        listPost.setAdapter(adapter);

//        cmt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), CommentsActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(intent);
//            }
//        });
        gotoMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MemberOptionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

    }
}
