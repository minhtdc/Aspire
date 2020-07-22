package com.example.aspire.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.aspire.R;
import com.example.aspire.data_models.NhanSu;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<NhanSu> {
    private Activity context;
    private int layoutID;
    private ArrayList<NhanSu> listMembers;


    public MyAdapter(Activity context, int resource, ArrayList<NhanSu> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listMembers = list;
    }

    //define view holder
    static class ViewHolder {
        ImageView imgUser;
        TextView txtName;
        TextView txtDes;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //create new view for the ListView
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = context.getLayoutInflater().inflate(layoutID, parent, false);

            viewHolder.txtName = (TextView) convertView.findViewById(R.id.userName);
            viewHolder.txtDes = (TextView) convertView.findViewById(R.id.userDesciption);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        }
        //re-uses the view in convertView
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //
        NhanSu nhanSu = listMembers.get(position);
        viewHolder.txtName.setText(nhanSu.getName());
        viewHolder.txtDes.setText(nhanSu.getDes());

        return convertView;
    }
}
