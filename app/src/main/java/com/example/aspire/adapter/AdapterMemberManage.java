package com.example.aspire.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.aspire.R;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterMemberManage extends ArrayAdapter<Users> {
    private Activity context;
    private int layoutID;
    private ArrayList<Users> listMembersManage;
    private boolean userLoggedIsAdminGroup = false;
    private String groupID;

    public AdapterMemberManage(Activity context, int resource, ArrayList<Users> listMemberUser, String groupID) {
        super(context, resource, listMemberUser);
        this.context = context;
        this.layoutID = resource;
        this.listMembersManage = listMemberUser;
        this.userLoggedIsAdminGroup = false;
        this.groupID = groupID;
    }

    //define view holder
    static class ViewHolder {
        TextView txt_fullName;
        TextView txt_avatar;
        TextView txt_task;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //create new view for the ListView
        final AdapterMemberManage.ViewHolder viewHolder;
        Users member = listMembersManage.get(position);
        final Users user = new Users();

        if (convertView == null) {
            //create new item for listview
            viewHolder = new AdapterMemberManage.ViewHolder();
            convertView = context.getLayoutInflater().inflate(layoutID, parent, false);

            viewHolder.txt_fullName = (TextView) convertView.findViewById(R.id.txt_fullName);
            viewHolder.txt_avatar = (TextView) convertView.findViewById(R.id.txt_avatar);

            Drawable test = ContextCompat.getDrawable(context, R.drawable.bg_circle_avatar);
            test.setColorFilter(Color.parseColor(member.getColorFavorite()), PorterDuff.Mode.MULTIPLY );
            viewHolder.txt_avatar.setBackground(test);

            viewHolder.txt_task = (TextView) convertView.findViewById(R.id.txt_task);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        }
        //re-uses the view in convertView
        else {
            viewHolder = (AdapterMemberManage.ViewHolder) convertView.getTag();
        }


        //
        viewHolder.txt_fullName.setText(member.getFullName());
        viewHolder.txt_avatar.setText(Character.toString(member.getFullName().toUpperCase().charAt(0)));
        viewHolder.txt_task.setText(member.getTask().equals("admin") ? "Quản trị viên" : "Thành viên");


        if (!userLoggedIsAdminGroup) {
            for (Users user_item : this.listMembersManage) {
                if (user_item.getTask().equals("admin") && user.getUserID().equals(user_item.getUserID())) {
                    userLoggedIsAdminGroup = true;
                    Toast.makeText(context, "Nhấn vào thành viên để xoá thành viên đó", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        } else {
            ListView listViewGroup = this.context.findViewById(R.id.listMember);
            listViewGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int positionSelected, long id) {
                    final Users userSelected = listMembersManage.get(positionSelected);
                    if (!userSelected.getTask().equals("admin")) {
                        AlertDialog.Builder dialogAnswer = new AlertDialog.Builder(context);
                        dialogAnswer.setTitle("Xác nhận");
                        dialogAnswer.setMessage("Bạn có muốn xoá thành viên này vào nhóm không?");

                        dialogAnswer.setNegativeButton("Xoá thành viên", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference db_ref_toListMember = database.getReference(
                                        String.format("/groups/%s/listMembers/%s", groupID, userSelected.getUserID()));
                                db_ref_toListMember.removeValue();
                                Toast.makeText(context, String.format("Thành viên %s đã bị xoá khỏi nhóm", userSelected.getFullName()),  Toast.LENGTH_LONG).show();
                            }
                        });

                        AlertDialog alertDialog = dialogAnswer.create();
                        alertDialog.show();
                    }
                }
            });
        }

        return convertView;
    }

}
