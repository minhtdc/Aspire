package com.example.aspire.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aspire.R;
import com.example.aspire.data_models.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPostListAdapter extends ArrayAdapter<Post> {
    private Activity context;
    private int layoutID;
    private ArrayList<Post> listPost;
    de.hdodenhof.circleimageview.CircleImageView userAvtCircle;
    TextView userName, userPosition, userTitlePost, userContentPost;
    protected FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser userCurrent = auth.getCurrentUser();
    DatabaseReference reference;
    public MyPostListAdapter(Activity context, int resource, ArrayList<Post> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listPost = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String userID, postID, postContent, postTitle;
        convertView = context.getLayoutInflater().inflate(layoutID, parent, false);
        userAvtCircle = (CircleImageView) convertView.findViewById(R.id.imgCircleAvtMem);
        userName = (TextView) convertView.findViewById(R.id.txtNameMember);
        userPosition = (TextView) convertView.findViewById(R.id.txtPositionMember);
        userTitlePost = (TextView) convertView.findViewById(R.id.txtTitlePostMember);
        userContentPost = (TextView) convertView.findViewById(R.id.txtContentPostMember);
        //userCountCommentPost = (TextView) convertView.findViewById(R.id.txtCountCommentMember);

        Post post = listPost.get(position);
        userAvtCircle.setImageResource(R.drawable.avt);
        userName.setText(reference.child(userCurrent.getUid()).child("fullName").toString());
        userPosition.setText("Thành viên");
        userTitlePost.setText(post.getPostTitle());
        userContentPost.setText(post.getPostContent());
        return convertView;
    }
}
