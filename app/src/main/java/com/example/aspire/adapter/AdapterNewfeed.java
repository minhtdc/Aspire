package com.example.aspire.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aspire.R;
import com.example.aspire.data_models.Groups;
import com.example.aspire.data_models.Users;

import java.util.ArrayList;

public class AdapterNewfeed extends ArrayAdapter<Groups> {
    private Activity context;
    private int layoutID;
    private ArrayList<Groups> listNew;

    public AdapterNewfeed(Activity context, int resource, ArrayList<Groups> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listNew = list;
    }

    //define view holder
    static class ViewHolder {
        TextView txtName;
        TextView txtPeople;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //create new view for the ListView
        final AdapterNewfeed.ViewHolder viewHolder;
        if (convertView == null) {
            //create new item for listview
            viewHolder = new AdapterNewfeed.ViewHolder();
            convertView = context.getLayoutInflater().inflate(layoutID, parent, false);

            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtuserName);
            viewHolder.txtPeople = (TextView) convertView.findViewById(R.id.txtPeople);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        }
        //re-uses the view in convertView
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //
        Groups groups = listNew.get(position);
        viewHolder.txtName.setText(groups.getGroupName());
        viewHolder.txtPeople.setText(groups.getGroupInfo());

        //sự kiện click lên list view
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kiểm tra xem người dùng này đã có trong nhóm chưa
                Users user = new Users();

            }
        });

        return convertView;
    }
}
