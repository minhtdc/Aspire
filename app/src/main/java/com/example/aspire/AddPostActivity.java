package com.example.aspire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.data_models.Groups;
import com.example.aspire.data_models.Post;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.io.Console;
import java.security.acl.Group;

public class AddPostActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    Button btnPost;
    de.hdodenhof.circleimageview.CircleImageView imgViewAva;
    TextView txtViewUsername;
    EditText edtTitle, edtContent;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_post_layout);

        btnPost = findViewById(R.id.btnPost);
        imgViewAva = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.imgViewAvatar);
        txtViewUsername = findViewById(R.id.txtViewUsername);
        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtDescription);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //lấy id group từ intent ở màn hình newfeed
        intent = AdapterNewfeed.intent;


       final String idGroup = (intent.getBundleExtra("group")).getString("groupID");

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTitle.getText().toString() != "" && edtContent.getText().toString() != "")
                {
                    Post post = new Post();
                    mDatabase = FirebaseDatabase.getInstance().getReference("groups").child(idGroup).child("posts");
                    String postID = mDatabase.push().getKey();
                    post.setGroupID(idGroup);
                    post.setPostID(postID);
                    post.setPostTitle(edtTitle.getText().toString());
                    post.setPostContent(edtContent.getText().toString());
                    Users user = new Users();
                    post.setUserID(user.getUserID());
                    try {
                        post.addPostToDatabase(post);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(AddPostActivity.this, "Tạo bài viết thành công", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(AddPostActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), PostListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
