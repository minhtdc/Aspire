package com.example.aspire.adapter;

import android.app.Activity;
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

    //define view holder
    static class ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView userAvatar;
        TextView txtViewUsername;
        TextView txtViewComment;
    }

    public AdapterComments(Activity context, int resource, ArrayList<Comments> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listComments = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = context.getLayoutInflater().inflate(layoutID, parent, false);
            viewHolder.userAvatar = (de.hdodenhof.circleimageview.CircleImageView) convertView.findViewById(R.id.imgViewAvatar);
            viewHolder.txtViewUsername = (TextView) convertView.findViewById(R.id.txtViewUsername);
            viewHolder.txtViewComment = (TextView) convertView.findViewById(R.id.txtViewCmt);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Comments comments = listComments.get(position);
        viewHolder.userAvatar.setImageResource(R.drawable.img_avatar_test);
        viewHolder.txtViewUsername.setText(comments.getUserName());
        viewHolder.txtViewComment.setText(comments.getUserComment());

        return convertView;
    }
}