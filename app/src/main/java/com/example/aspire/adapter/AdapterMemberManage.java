package com.example.aspire.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.aspire.R;
import com.example.aspire.data_models.MemberManage;
import com.example.aspire.data_models.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterMemberManage extends ArrayAdapter<MemberManage> {
    private Activity context;
    private int layoutID;
    private ArrayList<MemberManage> listMembersManage;

    public AdapterMemberManage(Activity context, int resource,  ArrayList<MemberManage> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listMembersManage = list;
    }
    //define view holder
    static class ViewHolder {
        ImageView imgUser;
        TextView txtName;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //create new view for the ListView
        final AdapterMemberManage.ViewHolder viewHolder;
        if (convertView == null) {
            //create new item for listview
            viewHolder = new AdapterMemberManage.ViewHolder();
            convertView = context.getLayoutInflater().inflate(layoutID, parent, false);

            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        }
        //re-uses the view in convertView
        else {
            viewHolder = (AdapterMemberManage.ViewHolder) convertView.getTag();
        }

        //
        MemberManage memberManage = listMembersManage.get(position);
        viewHolder.txtName.setText(memberManage.getUserName());


        return convertView;
    }

    public static class MyPostListAdapter extends ArrayAdapter<Post> {
        private Activity context;
        private int layoutID;
        private ArrayList<Post> listPost;
        de.hdodenhof.circleimageview.CircleImageView userAvtCircle;

        protected FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser userCurrent = auth.getCurrentUser();
        DatabaseReference reference;

        public MyPostListAdapter(Activity context, int resource, ArrayList<Post> list) {
            super(context, resource, list);
            this.context = context;
            this.layoutID = resource;
            this.listPost = list;
        }

        //define view holder
        static class ViewHolder {
            TextView userName, userPosition, userTitlePost, userContentPost;
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
                //userCountCommentPost = (TextView) convertView.findViewById(R.id.txtCountCommentMember);

                //binging the view in convertView coresponding
                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Post post = listPost.get(position);
            userAvtCircle.setImageResource(R.drawable.avt);
            viewHolder.userName.setText(reference.child(userCurrent.getUid()).child("fullName").toString());
            viewHolder.userPosition.setText("Thành viên");
            viewHolder.userTitlePost.setText(post.getPostTitle());
            viewHolder.userContentPost.setText(post.getPostContent());
            return convertView;
        }
    }
}
