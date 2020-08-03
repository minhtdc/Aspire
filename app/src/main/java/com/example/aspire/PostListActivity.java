package com.example.aspire;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.adapter.MyPostListAdapter;
import com.example.aspire.data_models.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PostListActivity extends AppCompatActivity {
    ListView listPost;
    private MyPostListAdapter adapter;
    private ArrayList<Post> listPostMember;
    Button cmt;
    Button gotoMember;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_list_layout);

        listPost = findViewById(R.id.listPost);
        cmt = findViewById(R.id.btnComment);
        gotoMember = findViewById(R.id.btnGoToMember);

        listPostMember = new ArrayList<Post>();

        adapter = new MyPostListAdapter(this, R.layout.post_detail_member_layout, listPostMember);
        listPost.setAdapter(adapter);

        //Lấy thông tin của Group từ màn AdapterNewFeed
        intent = AdapterNewfeed.intent;

        //Lấy groupID từ màn AdapterNewFeed thông qua intent
        String groupID = intent.getBundleExtra("group").getString("groupID");

        //Đưa dữ liệu từ db vào listView chứa các bài post trong nhóm
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference listPostInGroup = database.getReference("groups").child(groupID).child("posts");
        listPostInGroup.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                    //String key = data.getKey();
                    Post post = data.getValue(Post.class);
                    //post.setPostID(key);
                    adapter.add(post);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        //Nút Bình luận trong bài Post
//        cmt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), CommentsActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(intent);
//            }
//        });

//        //Nút Thành Viên
//        gotoMember.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MemberOptionActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(intent);
//            }
//        });

    }
}
