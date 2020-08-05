package com.example.aspire.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.example.aspire.R;
import com.example.aspire.android_2_func;
import com.example.aspire.data_models.Comments;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        final ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = context.getLayoutInflater().inflate(layoutID, parent, false);
            viewHolder.userAvatar = convertView.findViewById(R.id.imgViewAvatar);
            viewHolder.txtViewUsername = convertView.findViewById(R.id.txtViewUsername);
            viewHolder.txtViewComment = convertView.findViewById(R.id.txtViewCmt);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        final Comments comments = listComments.get(position);
        final Users userComment = new Users();

        DatabaseReference db_ref = FirebaseDatabase.getInstance()
                .getReference(String.format("/users/%s/", comments.getUserID()));
        db_ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    switch (data.getKey()) {
                        case "colorFavorite":
                            userComment.setColorFavorite(data.getValue().toString());
                            break;
                        case "fullName":
                            userComment.setFullName(data.getValue().toString());
                            break;
                        case "userAvatar":
                            userComment.setUserAvatar(data.getValue().toString());
                        case "userName":
                            userComment.setUserName(data.getValue().toString());
                        default:
                            break;
                    }
                }

                viewHolder.userAvatar.setImageResource(android_2_func.getFileImgByName(userComment.getUserAvatar()));
                viewHolder.txtViewUsername.setText(userComment.getFullName());
                viewHolder.txtViewComment.setText(comments.getContent());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return convertView;
    }
}