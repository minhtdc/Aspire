package com.example.aspire.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aspire.CreateGroupActivity;
import com.example.aspire.GroupManageActivity;
import com.example.aspire.JoinGroupActivity;
import com.example.aspire.NewFeedActivity;
import com.example.aspire.PersonPageActivity;
import com.example.aspire.PostListActivity;
import com.example.aspire.R;
import com.example.aspire.data_models.Groups;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterNewfeed extends ArrayAdapter<Groups> {
    private Activity context;
    private int layoutID;
    private ArrayList<Groups> listNew;
    public static Intent intent;

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
                final Users user = new Users();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("groups").child(getItem(position).getGroupID()).child("listMembers");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data : snapshot.getChildren()) {
                            String key = data.getKey();

                            //neu user da có trong nhóm
                            if(key.equals(user.getUserID())){
                                Intent intent = new Intent(context, PostListActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                context.startActivity(intent);
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                // nếu user chưa có trong nhóm

                //đưa dữ liệu vào intent
                Bundle data = new Bundle();
                data.putString("idUser", user.getUserID());
                data.putString("groupInfo", getItem(position).getGroupInfo());
                data.putString("adminID", getItem(position).getAdminID());
                data.putString("groupID", getItem(position).getGroupID());
                data.putString("groupName", getItem(position).getGroupName());



                intent = new Intent(context, JoinGroupActivity.class);
                intent.putExtra("group", data);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                context.startActivity(intent);







            }
        });

        return convertView;
    }
}
