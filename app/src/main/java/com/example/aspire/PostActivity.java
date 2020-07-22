package com.example.aspire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {
    ListView list;
    de.hdodenhof.circleimageview.CircleImageView userAvtCircle;
    TextView userName, userPosition, userTimePost, userTitlePost, userContentPost, userViewPost, userCountCommentPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_list_layout);

        //Find view
        userAvtCircle = findViewById(R.id.imgCircleAvtMem);
        userName = findViewById(R.id.txtNameMember);
        userPosition = findViewById(R.id.txtPositionMember);
        userTimePost = findViewById(R.id.txtTimePostMember);
        userTitlePost = findViewById(R.id.txtTitlePostMember);
        userContentPost = findViewById(R.id.txtContentPostMember);
        userViewPost = findViewById(R.id.txtViewPostMember);
        userCountCommentPost = findViewById(R.id.txtCountCommentMember);

        list = findViewById(R.id.listPost);
        ArrayList<Post> listPost = new ArrayList<Post>();
        Post post1 = new Post("Hihi", "Như Trần","Tiêu đề 1", "1 ngày trước", "Tiêu đề", "Nội dung nèeeee", "3 lượt xem", "5 bình luận");
        listPost.add(post1);

    }
}
