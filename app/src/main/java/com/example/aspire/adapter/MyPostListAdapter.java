package com.example.aspire.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPostListAdapter extends ArrayAdapter<Post> {
    private Activity context;
    private String idGroup;
    private int layoutID;
    private ArrayList<Post> listPost;

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
        TextView userName, userPosition, userTitlePost, userContentPost, txtCountCommentMember;
        Button btnComment, btn_deletePost;
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
            viewHolder.btnComment = (Button) convertView.findViewById(R.id.btnComment);
            viewHolder.btn_deletePost = (Button) convertView.findViewById(R.id.btn_deletePost);
            //userCountCommentPost = (TextView) convertView.findViewById(R.id.txtCountCommentMember);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Post post = listPost.get(position);

        viewHolder.userName.setText(post.getUserName());
        viewHolder.userPosition.setText("Thành viên");
        viewHolder.userTitlePost.setText(post.getPostTitle());
        viewHolder.userContentPost.setText(post.getPostContent());
        viewHolder.txtCountCommentMember.setText(post.getCommentCount() + " bình luận");

        //set event click button to go to list comment activity
        viewHolder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToListComment(context, post.getPostID(), idGroup);
            }
        });

        //set event click button to go to list comment activity
        viewHolder.btn_deletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
