package com.example.aspire.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aspire.MembersActivity;
import com.example.aspire.R;
import com.example.aspire.SwitchActivity;
import com.example.aspire.android_2_func;
import com.example.aspire.data_models.Post;
import com.example.aspire.data_models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPostListAdapter extends ArrayAdapter<Post> {
    private Activity context;
    private String idGroup, adminID;
    private int layoutID;
    private ArrayList<Post> listPost;

    public MyPostListAdapter(Activity context, int resource, ArrayList<Post> list, String idGroup, String adminID) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listPost = list;
        this.idGroup = idGroup;
        this.adminID = adminID;
    }

    //define view holder
    static class ViewHolder {
        TextView userName, userPosition, userTitlePost, userContentPost, txtCountCommentMember, txtBtn_comment, txt_countLike;
        Button btn_deletePost, btnLike;
        CircleImageView userAvtCircle;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            //create new item for listview
            viewHolder = new ViewHolder();
            convertView = context.getLayoutInflater().inflate(layoutID, parent, false);
            viewHolder.userAvtCircle = (CircleImageView) convertView.findViewById(R.id.imgCircleAvtMem);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.txtNameMember);
            viewHolder.userPosition = (TextView) convertView.findViewById(R.id.txtPositionMember);
            viewHolder.userTitlePost = (TextView) convertView.findViewById(R.id.txtTitlePostMember);
            viewHolder.userContentPost = (TextView) convertView.findViewById(R.id.txtContentPostMember);
            viewHolder.txtCountCommentMember = (TextView) convertView.findViewById(R.id.txtCountCommentMember);
            viewHolder.txt_countLike = (TextView) convertView.findViewById(R.id.txt_countLike);
            viewHolder.txtBtn_comment = (TextView) convertView.findViewById(R.id.txtBtn_comment);
            viewHolder.btnLike = (Button) convertView.findViewById(R.id.btnLike);
            viewHolder.btn_deletePost = (Button) convertView.findViewById(R.id.btn_deletePost);
            //userCountCommentPost = (TextView) convertView.findViewById(R.id.txtCountCommentMember);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Post post = listPost.get(position);

        viewHolder.userName.setText(post.getUserName());
        viewHolder.userPosition.setText(post.getUserID().equals(adminID) ? "Quản trị viên" : "Thành viên");
        viewHolder.userTitlePost.setText(post.getPostTitle());
        viewHolder.userContentPost.setText(post.getPostContent());
        viewHolder.txtCountCommentMember.setText(post.getCommentCount() + " bình luận");//Total comment this post

        //set event click button to go to list comment activity
        viewHolder.txtBtn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToListComment(context, post.getPostID(), idGroup);
            }
        });

        //Set total like
        DatabaseReference db_def_CountUserLiked = FirebaseDatabase.getInstance()
                .getReference(String.format("/groups/%s/posts/%s/like/", idGroup, post.getPostID()));
        db_def_CountUserLiked.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               viewHolder.txt_countLike.setText(snapshot.getChildrenCount() + " lượt thích");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //set event click button to go to list comment activity
        viewHolder.btn_deletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        final DatabaseReference db_def_userLiked = FirebaseDatabase.getInstance()
                .getReference(String.format("/groups/%s/posts/%s/like/%s/", idGroup, post.getPostID(), Users.ID_USER_LOGGED_IN));

        db_def_userLiked.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getKey() == null || snapshot.getValue() == null) {
                    viewHolder.btnLike.setText("Thích");
                    viewHolder.btnLike.setTextColor(Color.parseColor("#202020"));
                } else {
                    viewHolder.btnLike.setText("Bỏ thích");
                    viewHolder.btnLike.setTextColor(Color.parseColor("#2196F3"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Get event like post
        viewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.btnLike.getText().toString().equals("Thích")) {
                    JSONObject json = new JSONObject();
                    DatabaseReference db_def_like = FirebaseDatabase.getInstance().getReference(String.format("/groups/%s/posts/%s/like/", idGroup, post.getPostID()));
                    try {
                        json.put(Users.ID_USER_LOGGED_IN, "user_liked");
                        db_def_like.updateChildren(android_2_func.toMap(json));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    //Execute dislike
                    db_def_userLiked.removeValue();
                }
            }
        });

        //Set avatar for user this post
        DatabaseReference db_ref_userLogged = FirebaseDatabase.getInstance()
                .getReference(String.format("/users/%s/userAvatar/", post.getUserID()));
        db_ref_userLogged.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                viewHolder.userAvtCircle.setImageResource(android_2_func.getFileImgByName(snapshot.getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return convertView;
    }
}
