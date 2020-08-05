package com.example.aspire;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.adapter.MyPostListAdapter;
import com.example.aspire.data_models.Post;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PostListActivity extends AppCompatActivity {

    de.hdodenhof.circleimageview.CircleImageView userAvtCircle;
    TextView userName, userPosition, userTimePost, userTitlePost, userContentPost, userViewPost, userCountCommentPost;
    ListView listPost;
    private MyPostListAdapter adapter;
    private ArrayList<Post> listPostMember;
    Button cmt;
    Button btnSTT;
    Intent intent;
    TextView txtGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_list_layout);

        //Lấy thông tin của Group từ màn AdapterNewFeed
        intent = AdapterNewfeed.intent;
        //Lấy groupID từ màn AdapterNewFeed thông qua intent
        final String groupID = intent.getBundleExtra("group").getString("groupID");
        final String groupName = intent.getBundleExtra("group").getString("groupName");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(groupName.toUpperCase());
        toolbar.setTitleTextColor(Color.parseColor("navy"));
        setSupportActionBar(toolbar);

        listPost = findViewById(R.id.listPost);
        cmt = findViewById(R.id.btnComment);
        btnSTT = findViewById(R.id.btnSTT);

        listPostMember = new ArrayList<Post>();
        adapter = new MyPostListAdapter(this, R.layout.post_detail_member_layout, listPostMember, groupID);
        listPost.setAdapter(adapter);


        //Đưa dữ liệu từ db vào listView chứa các bài post trong nhóm
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference listPostInGroup = database.getReference("groups").child(groupID).child("posts");
        listPostInGroup.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();


                for (final DataSnapshot data : snapshot.getChildren()) {
                    final Post post = data.getValue(Post.class);

                    //Get total comment this post (post.getKey() this like post.ID)
                    DatabaseReference db_ref_Comments =
                            FirebaseDatabase.getInstance()
                                    .getReference(String.format("/groups/%s/posts/%s/comments/", groupID, post.getPostID()));
                    db_ref_Comments.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            post.setCommentCount((int) snapshot.getChildrenCount());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    final String idUsr = post.getUserID();
                    DatabaseReference UserName = FirebaseDatabase.getInstance().getReference("users").child(idUsr).child("fullName");
                    UserName.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String data = snapshot.getValue(String.class);
                            post.setUserName(data);
                            adapter.add(post);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        cmt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), CommentsActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(intent);
//            }
//        });
        btnSTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddPostActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        //Set avatar for user logged
        DatabaseReference db_ref_userLogged = FirebaseDatabase.getInstance()
                .getReference(String.format("/users/%s/userAvatar/", Users.ID_USER_LOGGED_IN));
        db_ref_userLogged.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                de.hdodenhof.circleimageview.CircleImageView imgViewAva = findViewById(R.id.img_avatar_userLogged);
                imgViewAva.setImageResource(android_2_func.getFileImgByName(snapshot.getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //Nút Bình luận trong bài Post
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_gotoListMember:
                intent = new Intent(getApplicationContext(), MembersActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;

            case R.id.action_gotoListRequest:
                intent = new Intent(getApplicationContext(), MemberOptionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
