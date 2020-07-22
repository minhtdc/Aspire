package com.example.aspire;

import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_list_layout);

        listPost = findViewById(R.id.listPost);


        listPostMember = new ArrayList<Post>();
        Post post1 = new Post("Nhu Tran", "Thanh vien", "1 gio truoc", "Tieu de test 1",
                "Noi dung testb 1 ", "2 luot xem", "4 binh luan");
        Post post2 = new Post("Nhu Tran", "Thanh vien",
                "1 gio truoc", "Tieu de test 1", "Noi dung testb 1 ", "2 luot xem",
                "4 binh luan");
        listPostMember.add(post1);
        listPostMember.add(post2);
        adapter = new MyPostListAdapter(this, R.layout.post_detail_member_layout,listPostMember);

        listPost.setAdapter(adapter);

    }
}
