package com.example.aspire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.data_models.Groups;
import com.example.aspire.data_models.Requests;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

public class JoinGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final TextView txtGroupInfo;
        final EditText edtGroupJoin;
        final Button btnJoin, btnCancel;
        final Intent intent;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_group_layout);
        txtGroupInfo = findViewById(R.id.txtGroupInfo);
        edtGroupJoin = findViewById(R.id.edtGroupJoin);
        btnJoin = findViewById(R.id.btnJoin);
        btnCancel = findViewById(R.id.btnCancel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        final Requests request = new Requests();

        //Lấy thông tin nhpms từ màn hình newfeed
        intent = AdapterNewfeed.intent;

        txtGroupInfo.setText("Tên nhóm: " +(intent.getBundleExtra("group")).getString("groupName") + "\n\n"
                + "Thông tin nhóm: " + (intent.getBundleExtra("group")).getString("groupInfo"));

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnJoin.getText().toString() == "Hủy yêu cầu") {
                    DatabaseReference remove = FirebaseDatabase.getInstance().getReference("requests").child(request.getGroupID());
                    remove.removeValue();
                    Toast.makeText(JoinGroupActivity.this, "Hủy yêu cầu thành công", Toast.LENGTH_SHORT).show();

                    btnJoin.setText("Tham gia");
                }
                //
                else{
                    request.setGroupID((intent.getBundleExtra("group")).getString("groupID"));
                    request.setAdminID((intent.getBundleExtra("group")).getString("adminID"));
                    request.setContent(edtGroupJoin.getText().toString());
                    Users user = new Users();
                    request.setMemberID(user.getUserID());
                        try {
                            request.addRequestsToDatabase(request);
                            Toast.makeText(JoinGroupActivity.this, "Gửi yêu càu thành công", Toast.LENGTH_SHORT).show();
                            btnJoin.setText("Hủy yêu cầu");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }

            }
        });

    }
}
