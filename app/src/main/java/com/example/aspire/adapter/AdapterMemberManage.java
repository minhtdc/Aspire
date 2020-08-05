package com.example.aspire.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.aspire.R;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterMemberManage extends ArrayAdapter<Users> {
    private Activity context;
    private int layoutID;
    private ArrayList<Users> listMembersManage;
    private Intent intent;

    public AdapterMemberManage(Activity context, int resource, ArrayList<Users> list) {
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
        final Users member = listMembersManage.get(position);
        viewHolder.txtName.setText(member.getFullName());

        //Lấy groupID từ màn AdapterNewFeed thông qua intent
        intent = AdapterNewfeed.intent;
        final String groupID = intent.getBundleExtra("group").getString("groupID");

        //kiểm tra xem nếu là admin thì có quyền xóa thành viên đó ra khỏi nhóm
        //lấy id admin của group
        final DatabaseReference getAD = FirebaseDatabase.getInstance().getReference("groups").child(groupID).child("adminID");

        //sự kiện kick thành viên ra khỏi nhóm
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAD.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String adminID = snapshot.getValue(String.class);
                        // nếu admin của group bằng user hiện tại thì có quyền xóa member
                        Users user = new Users();
                        final String userCurrent = user.getUserID();
                        if (adminID.equals(user.getUserID())) {
                            //tạo hộp thoại
                            AlertDialog.Builder qs = new AlertDialog.Builder(context);
                            //thiết lập tiêu đề
                            qs.setTitle("Xác nhận");
                            qs.setMessage("Bạn có muốn xóa thành viên này vào khỏi không?");
                            //nút đồng ý
                            qs.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    final DatabaseReference myRef = database.getReference("groups").child(groupID).child("listMembers").child(getItem(position).getUserID());
                                    String admin = myRef.getKey();
                                    if(admin.equals(userCurrent)){
                                        Toast.makeText(context, "Đây là admin, Không được xóa",Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        myRef.removeValue();
                                        Toast.makeText(context, "Xóa thành công",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                            //nút từ chối
                            qs.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            //Tạo dialog
                            AlertDialog al = qs.create();
                            //Hiển thị
                            al.show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        return convertView;
    }

}
