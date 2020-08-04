package com.example.aspire;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aspire.adapter.AdapterComments;
import com.example.aspire.data_models.Comments;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {
    private String idGroup, idPost, idCommentAdd;
    private AdapterComments adapterComments;
    private ArrayList<Comments> listComments;
    private ListView listView;
    private Toolbar toolbar;
    private TextView txt_inToolbar;
    private ImageButton imgBtn_inToolbar;
    private Button btnPostCmt;
    private EditText text;
    private String username = "";

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_layout);
        getAllInformation();

        //get view from layout
        listView = findViewById(R.id.listViewComments);
        //test add dữ liệu
        listComments = new ArrayList<Comments>();
        adapterComments = new AdapterComments(this, R.layout.comment_item, listComments);
        listView.setAdapter(adapterComments);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("groups").child(idGroup).child("posts").child(idPost);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                adapterComments.clear();
//                for (DataSnapshot data : snapshot.getChildren()) {
//                    String key = data.getKey();
//                    Comments group = data.getValue(Comments.class);
//                    group.setGroupID(key);
//                    adapterComments.add(group);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        text = (EditText) findViewById(R.id.edtWriteComments);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getAllInformation();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnPostCmt = findViewById(R.id.btnPostCmt);
        btnPostCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllInformation();
                String contentComment = text.getText().toString();
                if (!contentComment.equals("")) {
                    Comments comments = new Comments(username, contentComment);
                    try {
                        comments.addComment(databaseReference.push().getKey(), idGroup, idPost, comments);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //Set information in toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Bình luận");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToListComment(CommentsActivity.this, idPost, idGroup);
                finish();
            }
        });
    }

    private void getAllInformation() {
        final Users user = new Users();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child(user.getUserID()).child("userName");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                username = data;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Get value
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        idPost = bundle.getString("idPost");
        idGroup = bundle.getString("idGroup");
    }
}