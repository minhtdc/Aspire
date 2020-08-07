package com.example.aspire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

    //Author: Tran Minh Phuc 06-08-2020
    Toolbar toolbar;
    TextView txt_inToolbar;
    ImageButton imgBtn_inToolbar;

    Button btnPost;
    de.hdodenhof.circleimageview.CircleImageView imgViewAva;
    TextView txtViewUsername;
    EditText edtTitle, edtContent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_post_layout);

        //Extract the data…
        bundle = getIntent().getExtras();
        final String idGroup = bundle.getString("groupID");
        final String idUser = Users.ID_USER_LOGGED_IN;

        //Author: Tran Minh Phuc 06-08-2020
        btnPost = findViewById(R.id.btnPost);
        imgViewAva = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.imgViewAvatar);
        txtViewUsername = findViewById(R.id.txtViewUsername);
        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtDescription);
        //Set information in toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Tạo bài viết");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToListPost(AddPostActivity.this, idGroup);
                finish();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtTitle.getText().toString().equals("") && !edtContent.getText().toString().equals("")) {
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
                    SwitchActivity.goToListPost(AddPostActivity.this, idGroup);
                    finish();

                } else {
                    Toast.makeText(AddPostActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtTitle.requestFocus();
        //Set avatar for user logged
        DatabaseReference db_ref_userLogged = FirebaseDatabase.getInstance()
                .getReference(String.format("/users/%s/", Users.ID_USER_LOGGED_IN));
        db_ref_userLogged.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    switch (data.getKey()) {
                        case "colorFavorite":
                            break;
                        case "userAvatar":
                            imgViewAva.setImageResource(android_2_func.getFileImgByName(data.getValue().toString()));
                            break;
                        case "fullName":
                            txtViewUsername.setText(data.getValue().toString());;
                            break;
                        case "userName":
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
