package com.example.aspire.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aspire.R;
import com.example.aspire.data_models.Comments;

import java.util.ArrayList;

public class AdapterComments extends ArrayAdapter<Comments> {
    private Activity context;
    private int layoutID;
    private ArrayList<Comments> listComments;
    LayoutInflater inflater;
    //
    de.hdodenhof.circleimageview.CircleImageView userAvatar;
    TextView txtViewUsername;
    TextView txtViewComment;

    public AdapterComments(Activity context, int resource, ArrayList<Comments> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listComments = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = context.getLayoutInflater().inflate(layoutID, parent, false);
            userAvatar = (de.hdodenhof.circleimageview.CircleImageView) convertView.findViewById(R.id.imgViewAvatar);
            txtViewUsername = (TextView) convertView.findViewById(R.id.txtViewUsername);
            txtViewComment = (TextView) convertView.findViewById(R.id.txtViewCmt);

            Comments comments =listComments.get(position);
            userAvatar.setImageResource(R.drawable.anhdaidien);
            txtViewUsername.setText(comments.getUserName());
            txtViewComment.setText(comments.getUserComment());


       return convertView;
    }
}