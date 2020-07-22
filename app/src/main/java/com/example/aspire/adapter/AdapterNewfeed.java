package com.example.aspire.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.aspire.R;
import com.example.aspire.data_models.Newfeed;
import com.example.aspire.data_models.Users;

import java.util.ArrayList;

public class AdapterNewfeed extends ArrayAdapter<Newfeed> {
    private Activity context;
    private int layoutID;
    private ArrayList<Newfeed> listNew;

    public AdapterNewfeed(Activity context, int resource, ArrayList<Newfeed> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutID = resource;
        this.listNew = list;
    }

    //define view holder
    static class ViewHolder {
        ImageView imgUser;
        EditText edtName;
        EditText edtPeople;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //create new view for the ListView
        final AdapterNewfeed.ViewHolder viewHolder;
        if (convertView == null) {
            //create new item for listview
            viewHolder = new AdapterNewfeed.ViewHolder();
            convertView = context.getLayoutInflater().inflate(layoutID, parent, false);


            viewHolder.imgUser =(ImageView) convertView.findViewById(R.id.imgHinhAnh);
            viewHolder.edtName = (EditText) convertView.findViewById(R.id.edtuserName);
            viewHolder.edtPeople = (EditText) convertView.findViewById(R.id.edtPeople);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        }
        //re-uses the view in convertView
        else {
            viewHolder = (AdapterNewfeed.ViewHolder) convertView.getTag();
        }

        //
        Newfeed newfeed = listNew.get(position);
        viewHolder.edtName.setText(newfeed.getUserName());
        viewHolder.edtPeople.setText(newfeed.getUserPeople());

        return convertView;
    }
}
