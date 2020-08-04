package com.example.aspire.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aspire.R;
import com.example.aspire.SwitchActivity;
import com.example.aspire.data_models.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPostListAdapter extends ArrayAdapter<Post> {
    private Activity context;
    private String idGroup;
    private int layoutID;
    private ArrayList<Post> listPost;
    CircleImageView userAvtCircle;

    protected FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser userCurrent = auth.getCurrentUser();
    DatabaseReference reference;

    public MyPostListAdapter(Activity context, int resource, ArrayList<Post> list, String idGroup) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listPost = list;
        this.idGroup = idGroup;
    }

    //define view holder
    static class ViewHolder {
        TextView userName, userPosition, userTitlePost, userContentPost;
        Button btnComment;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            //create new item for listview
            viewHolder = new ViewHolder();
            convertView = context.getLayoutInflater().inflate(layoutID, parent, false);
            userAvtCircle = (CircleImageView) convertView.findViewById(R.id.imgCircleAvtMem);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.txtNameMember);
            viewHolder.userPosition = (TextView) convertView.findViewById(R.id.txtPositionMember);
            viewHolder.userTitlePost = (TextView) convertView.findViewById(R.id.txtTitlePostMember);
            viewHolder.userContentPost = (TextView) convertView.findViewById(R.id.txtContentPostMember);
            viewHolder.btnComment = (Button) convertView.findViewById(R.id.btnComment);
            //userCountCommentPost = (TextView) convertView.findViewById(R.id.txtCountCommentMember);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Post post = listPost.get(position);
        userAvtCircle.setImageResource(R.drawable.avt);
        viewHolder.userName.setText(post.getUserName());
        viewHolder.userPosition.setText("Thành viên");
        viewHolder.userTitlePost.setText(post.getPostTitle());
        viewHolder.userContentPost.setText(post.getPostContent());

        //set event click button to go to list comment activity
        viewHolder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToListComment(context, post.getPostID(), idGroup);
            }
        });

        return convertView;
    }
}
