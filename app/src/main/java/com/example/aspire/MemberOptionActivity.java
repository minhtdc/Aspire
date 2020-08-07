package com.example.aspire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aspire.adapter.AdapterMemberOption;
import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.data_models.Requests;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MemberOptionActivity extends AppCompatActivity {
    //Author: Tran Minh Phuc 06-08-2020
    Toolbar toolbar;
    TextView txt_inToolbar;
    ImageButton imgBtn_inToolbar;

    private android_2_func android_2_func = new android_2_func();
    private AdapterMemberOption adapter;
    private ArrayList<Requests> listMembers;
    ListView listView;
    private Bundle bundle;
    private String idGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_option_layout);

        //Author: Tran Minh Phuc 06-08-2020'
        //Set information in toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Duyệt thành viên");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberOptionActivity.this, PostListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });

        //Get id grouop by getIntent().getExtras()
        bundle = getIntent().getExtras();
        idGroup = bundle.getString("groupID");

        listView = findViewById(R.id.listMember);
        listMembers = new ArrayList<Requests>();
        adapter = new AdapterMemberOption(this, R.layout.list_member_option_layout, listMembers, idGroup);
        listView.setAdapter(adapter);


        //lấy đối tượng FirebaseDatabase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Kết nối tới node có tên là contacts (node này do ta định nghĩa trong CSDL Firebase)
        DatabaseReference myRef = database.getReference("groups").child(idGroup).child("requests");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                //vòng lặp để lấy dữ liệu khi có sự thay đổi trên Firebase
                for (DataSnapshot data : snapshot.getChildren()) {
                    //lấy key của dữ liệu
                    String key = data.getKey();
                    //lấy giá trị của key (nội dung)
                    final Requests request = data.getValue(Requests.class);
                    request.setMemberID(key);


                    DatabaseReference db_refUser = FirebaseDatabase.getInstance().getReference(String.format("/users/%s/", key));
                    db_refUser.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Users user = new Users();
                            for (DataSnapshot data : snapshot.getChildren()) {
                                switch (data.getKey()) {
                                    case "fullName":
                                        user.setFullName(data.getValue().toString());
                                        break;
                                    case "userAvatar":
                                        user.setUserAvatar(data.getValue().toString());
                                        break;
                                    case "colorFavorite":
                                        user.setColorFavorite(data.getValue().toString());
                                        break;
                                    case "userName":
                                        user.setUserName(data.getValue().toString());
                                        break;
                                }
                            }

                            request.setUser(user);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.add(request);
                        }
                    },500);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
