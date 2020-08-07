package com.example.aspire.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.aspire.PostListActivity;
import com.example.aspire.R;
import com.example.aspire.android_2_func;
import com.example.aspire.data_models.Groups;
import com.example.aspire.data_models.Requests;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.util.ArrayList;

public class AdapterNewfeed extends ArrayAdapter<Groups> {
    private Activity context;
    private int layoutID;
    private ArrayList<Groups> listNew;
    public static Intent intent;
    AlertDialog.Builder alertDialog;
    private android_2_func android_2_func;
    private boolean showDialog = false;

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
        TextView txt_avatarGroup;
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
            viewHolder.txt_avatarGroup = (TextView) convertView.findViewById(R.id.txt_avatarGroup);

            Drawable test = ContextCompat.getDrawable(context, R.drawable.bg_circle_avatar);
            test.setColorFilter(Color.parseColor(android_2_func.getRandomColor()), PorterDuff.Mode.MULTIPLY);
            viewHolder.txt_avatarGroup.setBackground(test);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        }
        //re-uses the view in convertView
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //
        final Groups groups = listNew.get(position);
        viewHolder.txtName.setText(groups.getGroupName());
        viewHolder.txtPeople.setText(groups.getGroupInfo());
        viewHolder.txt_avatarGroup.setText(Character.toString(viewHolder.txtName.getText().toString().toUpperCase().charAt(0)));

        //sự kiện click lên list view
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kiểm tra xem người dùng này đã có trong nhóm chưa
                final Users user = new Users();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                android_2_func = new android_2_func();
                alertDialog = new AlertDialog.Builder(context);
                showDialog = false;

                /*
                 * Author: Tran Minh Phuc 06-08-2020
                 * */
                //Check user register has in list "../request/"
                DatabaseReference myRef = database.getReference(String.format("/groups/%s/listMembers/%s/", groups.getGroupID(), user.getUserID()));
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Bundle data = new Bundle();
                        Intent intent;

                        if (snapshot.getValue() != null) {
                            data.putString("idUser", user.getUserID());
                            data.putString("groupID", getItem(position).getGroupID());
                            data.putString("groupName", getItem(position).getGroupName());

                            intent = new Intent(context, PostListActivity.class);
                            intent.putExtras(data);
                            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            context.startActivity(intent);
                        } else {
                            android_2_func.showLoading(context);
                            final DatabaseReference db_ref_HasUserRegister = database
                                    .getReference(String.format("/groups/%s/requests/%s/", groups.getGroupID(), user.getUserID()));
                            db_ref_HasUserRegister.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.getValue() == null) // if value null, then user register no in list request group
                                    {
                                        showDialog = true;
                                        final EditText input = new EditText(context);
                                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                                        input.requestFocus();
                                        input.setHint("Có thể để trống");

                                        alertDialog.setMessage("Bạn chưa là thành viên của nhóm này, hãy gửi thông điệp gì đó để xin vào nhóm đã chọn.")
                                                .setTitle("Xin vào nhóm")
                                                .setView(input)
                                                .setPositiveButton("Gửi", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        final Requests request = new Requests();
                                                        request.setGroupID(getItem(position).getGroupID());
                                                        request.setMemberID(Users.ID_USER_LOGGED_IN);
                                                        request.setContent(input.getText().toString());

                                                        try {
                                                            request.addRequestsToDatabase(request);
                                                            Toast.makeText(context, "Gửi yêu càu thành công", Toast.LENGTH_SHORT).show();
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                        dialog.cancel();
                                                    }
                                                })
                                                .setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.cancel();
                                                    }
                                                });
                                    } else {
                                        showDialog = true;
                                        alertDialog.setTitle("Đã đăng ký nhóm")
                                                .setMessage("Hãy đợi quản trị viên duyệt yêu cầu của bạn, nếu không bạn có thể huỷ. \nBạn có muốn huỷ yêu cầu không?")
                                                .setPositiveButton("Huỷ yêu cầu", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        db_ref_HasUserRegister.removeValue();
                                                        Toast.makeText(context, "Hủy yêu cầu thành công", Toast.LENGTH_SHORT).show();
                                                        dialog.cancel();
                                                    }
                                                });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (showDialog) {
                            alertDialog.show();
                            android_2_func.closeLoading();
                        }
                    }
                }, 500);
            }
        });

        return convertView;
    }
}
