package com.example.aspire.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aspire.R;
import com.example.aspire.data_models.Post;

import java.util.ArrayList;

public class MyPostListAdapter extends ArrayAdapter<Post> {
    private Activity context;
    private int layoutID;
    private ArrayList<Post> listPost;
    de.hdodenhof.circleimageview.CircleImageView userAvtCircle;
    TextView userName, userPosition, userTimePost, userTitlePost, userContentPost, userViewPost, userCountCommentPost;
    public MyPostListAdapter(Activity context, int resource, ArrayList<Post> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listPost = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = context.getLayoutInflater().inflate(layoutID, parent, false);
        userAvtCircle = (de.hdodenhof.circleimageview.CircleImageView) convertView.findViewById(R.id.imgCircleAvtMem);
        userName = (TextView) convertView.findViewById(R.id.txtNameMember);
        userPosition = (TextView) convertView.findViewById(R.id.txtPositionMember);
        userTimePost = (TextView) convertView.findViewById(R.id.txtTimePostMember);
        userTitlePost = (TextView) convertView.findViewById(R.id.txtTitlePostMember);
        userContentPost = (TextView) convertView.findViewById(R.id.txtContentPostMember);
        userViewPost = (TextView) convertView.findViewById(R.id.txtViewPostMember);
        userCountCommentPost = (TextView) convertView.findViewById(R.id.txtCountCommentMember);

        Post post = listPost.get(position);
        userAvtCircle.setImageResource(R.drawable.avt);
        userName.setText(post.getUserName());
        userPosition.setText(post.getUserPosition());
        userTimePost.setText(post.getUserTimePost());
        userTitlePost.setText(post.getUserTitlePost());
        userContentPost.setText(post.getUserContentPost());
        userViewPost.setText(post.getUserViewPost());
        userCountCommentPost.setText(post.getUserCountCommentPost());

        return convertView;
    }
}
