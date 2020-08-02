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


import com.example.aspire.R;
import com.example.aspire.data_models.MemberManage;
import com.example.aspire.data_models.Requests;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterMemberManage extends ArrayAdapter<Requests> {
    private Activity context;
    private int layoutID;
    private ArrayList<Requests> listMembersManage;
    private Intent intent;

    public AdapterMemberManage(Activity context, int resource,  ArrayList<Requests> list) {
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
        final String idGroup = (intent.getBundleExtra("group")).getString("groupID");
        //
        Requests requests = listMembersManage.get(position);
        viewHolder.txtName.setText(requests.getMemberID());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tạo hộp thoại
                AlertDialog.Builder qs = new AlertDialog.Builder(context);
                //thiết lập tiêu đề
                qs.setTitle("Xác nhận");
                qs.setMessage("Bạn có muốn xóa thành viên không?");
                //nút đồng ý
                qs.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("groups").child(idGroup).child("listMembersManage").child(getItem(position).getMemberID());
                        myRef.setValue("members");
                        Toast.makeText(context, "Xoá thành công",Toast.LENGTH_SHORT).show();
                        DatabaseReference del = database.getReference("groups").child(idGroup).child("members").child(getItem(position).getMemberID());
                        del.removeValue();

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
