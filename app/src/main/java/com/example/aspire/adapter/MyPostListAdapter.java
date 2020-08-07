package com.example.aspire.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aspire.R;
import com.example.aspire.SwitchActivity;
import com.example.aspire.data_models.Post;
import com.example.aspire.data_models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private Intent intent;

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
        Button btnComment, btnDelPost;
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
            viewHolder.btnDelPost = (Button) convertView.findViewById(R.id.btnDelPost);
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

        //lay id group
        intent = AdapterNewfeed.intent;
        final String idGroup = (intent.getBundleExtra("group")).getString("groupID");
        final String adminGroup = (intent.getBundleExtra("group")).getString("adminID");
        Users user = new Users();
        //neu la admin
        if(adminGroup.equals(user.getUserID())) {
            viewHolder.btnDelPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //tạo hộp thoại
                    AlertDialog.Builder qs = new AlertDialog.Builder(context);
                    //thiết lập tiêu đề
                    qs.setTitle("Xác nhận");
                    qs.setMessage("Bạn có muốn xóa bài viết này không?");
                    //nút đồng ý
                    qs.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseReference del = FirebaseDatabase.getInstance().getReference("groups").child(idGroup).child("posts").child(getItem(position).getPostID());
                            del.removeValue();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //nút từ chối
                    qs.setNegativeButton("Từ chối", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { }
                    });

                    //Tạo dialog
                    AlertDialog al = qs.create();
                    //Hiển thị
                    al.show();
                }
            });
        }
        //nếu là chủ bài viết
        else {
            viewHolder.btnDelPost.setVisibility(View.GONE);
        }

        return convertView;
    }
}
