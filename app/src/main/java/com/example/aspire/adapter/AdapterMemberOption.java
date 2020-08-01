package com.example.aspire.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.aspire.R;
import com.example.aspire.data_models.Requests;
import com.example.aspire.data_models.Users;

import java.util.ArrayList;

public class AdapterMemberOption extends ArrayAdapter<Requests> {
    private Activity context;
    private int layoutID;
    private ArrayList<Requests> listMembers;


    public AdapterMemberOption(Activity context, int resource, ArrayList<Requests> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listMembers = list;
    }

    //define view holder
    static class ViewHolder {
        ImageView userAvata;
        TextView userName;
        TextView userDetail;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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
        viewHolder.userName.setText(requests.getMemberID());
        viewHolder.userDetail.setText(requests.getContent());

        return convertView;
    }
}
