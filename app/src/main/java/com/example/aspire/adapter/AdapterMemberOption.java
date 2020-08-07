package com.example.aspire.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.aspire.MemberOptionActivity;
import com.example.aspire.R;
import com.example.aspire.android_2_func;
import com.example.aspire.data_models.Requests;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterMemberOption extends ArrayAdapter<Requests> {
    private Activity context;
    private int layoutID;
    private ArrayList<Requests> listMembers;
    private Intent intent;
    private String idGroup;


    public AdapterMemberOption(Activity context, int resource, ArrayList<Requests> list, String idGroup) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listMembers = list;
        this.idGroup = idGroup;
    }

    //define view holder
    static class ViewHolder {
        ImageView userAvata;
        TextView userName;
        TextView userDetail;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        //create new view for the ListView
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = context.getLayoutInflater().inflate(layoutID, parent, false);

            viewHolder.userAvata = (ImageView) convertView.findViewById(R.id.userAvatar);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.userName);
            viewHolder.userDetail = (TextView) convertView.findViewById(R.id.userDesciption);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        }
        //re-uses the view in convertView
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //
        Requests requests = listMembers.get(position);
        viewHolder.userDetail.setText(requests.getContent().equals("") ? "Nội xin vào nhóm rỗng" : requests.getContent());
        viewHolder.userAvata.setImageResource(android_2_func.getFileImgByName(requests.getUser().getUserAvatar()));
        viewHolder.userName.setText(requests.getUser().getFullName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tạo hộp thoại
                AlertDialog.Builder qs = new AlertDialog.Builder(context);
                //thiết lập tiêu đề
                qs.setTitle("Xác nhận");
                qs.setMessage("Bạn có muốn thêm thành viên này vào nhóm không?");
                //nút đồng ý
                qs.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("groups").child(idGroup).child("listMembers").child(getItem(position).getMemberID());
                        myRef.setValue("member");
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        DatabaseReference del = database.getReference("groups").child(idGroup).child("requests").child(getItem(position).getMemberID());
                        del.removeValue();

                    }
                });

                //nút từ chối
                qs.setNegativeButton("Từ chối", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("groups").child(idGroup).child("requests").child(getItem(position).getMemberID());
                        myRef.removeValue();
                        Toast.makeText(context, "Từ chối thành công", Toast.LENGTH_SHORT).show();

                    }
                });

                //Tạo dialog
                AlertDialog al = qs.create();
                //Hiển thị
                al.show();

            }
        });

        return convertView;
    }


}

