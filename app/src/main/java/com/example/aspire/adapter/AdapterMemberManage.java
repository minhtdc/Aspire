package com.example.aspire.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.aspire.R;
import com.example.aspire.data_models.MemberManage;

import java.util.ArrayList;

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

}
